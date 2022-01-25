package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import model.AppThingComments;
import model.AppThingPart;
import model.AppThingParts;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppCommandSaveMsgTest {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @LocalServerPort private int port;

  @Autowired private CommandBusDrivingApp commandBusDrivingApp;

  @Autowired private ThingEntityRepository thingRepository;

  @Autowired private EmbeddedActiveMQ embeddedActiveMQ;

  @Autowired private CommandHandlerDrivenFrmSaveMsgListener commandHandlerDrivenFrmSaveMsgListener;

  // @Autowired private EventQueueListener msgInQueueListener;

  @Test
  @Transactional
  public void testCreateThingCommandVolume() {

    int nbrMsgs = 100;

    long sent = 0;

    final boolean[] notDone = {true};
    Object lock = new Object();

    PropertyChangeListener pcl =
        new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            synchronized (lock) {
              Integer newVal = (Integer) evt.getNewValue();
              if (newVal.intValue() >= nbrMsgs) {
                notDone[0] = false;
              }
              lock.notify();
            }
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
              .thingNbr(sent)
              .comments(thingComments)
              .parts(thingParts)
              .build();
      commandBusDrivingApp.perform(appThingCommandSave);
      sent++;
    }

    synchronized (lock) {
      while (true) {
        try {
          if (notDone[0] == false) {
            break;
          }
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    long end = System.currentTimeMillis();
    System.out.println("msg# " + sent + " time " + (end - strt));
    ThingEntity thingEntity = thingRepository.findDistinctByThingNbr(10L);
    assertEquals(10L, thingEntity.getThingNbr());
    LOGGER.info(thingEntity.toString());
  }
}
