package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.app.model.AppThingComments;
import co.ex.app.model.AppThingPart;
import co.ex.app.model.AppThingParts;
import co.ex.frmwrk.config.FrmWrkConfig;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppCommandSaveMsgTest {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @LocalServerPort
    private int port;

    @Autowired
    AppSetupMapBeans appSetupMapBeans;

    private CommandBusDrivingApp commandBusDrivingApp;

    @Autowired
    private ThingEntityRepository thingRepository;

    @Autowired
    private EmbeddedActiveMQ embeddedActiveMQ;

    @Autowired
    private CommandHandlerDrivenFrmSaveMsgListener commandHandlerDrivenFrmSaveMsgListener;

    @Autowired
    private EventQueueListener msgInQueueListener;

    @Test
    @Transactional
    public void testCreateThingCommandVolume() {

        commandBusDrivingApp = appSetupMapBeans.getCommandBusDrivingApp();

        int nbrMsgs = 10;

        long sent = 0;

        final boolean[] done = {false};
        Object lock = new Object();

        PropertyChangeListener pcl =
                evt -> {
                    synchronized (lock) {
                        Integer newVal = (Integer) evt.getNewValue();
                        if (newVal.intValue() >= nbrMsgs) {
                            done[0] = true;
                        }
                        lock.notify();
                    }
                };
        commandHandlerDrivenFrmSaveMsgListener.addPropertyChangeListener(pcl);

        AppThingPart thingPart1 = AppThingPart.builder().partId("1").qty(1).build();
        AppThingPart thingPart2 = AppThingPart.builder().partId("2").qty(2).build();
        AppThingPart thingPart3 = AppThingPart.builder().partId("3").qty(3).build();

        List<AppThingPart> appParts =
                new ArrayList<>(Arrays.asList(thingPart1, thingPart2, thingPart3));
        AppThingParts thingParts = AppThingParts.builder().parts(appParts).build();

        AppThingComments thingComments =
                AppThingComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();

        long strt = System.currentTimeMillis();

        for (int i = 0; i < nbrMsgs; i++) {
            AppThingCommandSave appThingCommandSave =
                    AppThingCommandSave.builder()
                            .uuid( UUID.randomUUID() )
                            .thingNbr(null)
                            .comments(thingComments)
                            .parts(thingParts)
                            .build();
            commandBusDrivingApp.perform(appThingCommandSave);
            sent++;
        }

        synchronized (lock) {
            while (true) {
                try {
                    if (done[0] == true) {
                        break;
                    }
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("start listing");
        List<ThingEntity> thingEntities = thingRepository.findByThingNbrOrderByDttm(10L);
        assertEquals(nbrMsgs, thingEntities.size());
        thingEntities.stream()
                .forEach(
                        e -> {
                            System.out.println("*** " + e.toString());
                        });
        System.out.println("end listing");
    }
}
