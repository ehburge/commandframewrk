package co.ex.frmwrk.gateway.msg.impl;

import cmd.impl.AppThingCommandSave;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.gateway.persist.ThingComment;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.gateway.persist.ThingPart;
import model.AppThingPart;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppCommandSaveMsgTest {

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

    AppThingPart thingPart1 = new AppThingPart(1, "part 1 desc", new BigDecimal(123.45));
    AppThingPart thingPart2 = new AppThingPart(2, "part 2 desc", new BigDecimal(234.56));
    AppThingPart thingPart3 = new AppThingPart(3, "part 3 desc", new BigDecimal(345.56));

    List<AppThingPart> appParts = new ArrayList<>(Arrays.asList(thingPart1, thingPart2, thingPart3));
    long strt = System.currentTimeMillis();

    for (int i = 0; i < nbrMsgs; i++) {
      AppThingCommandSave appThingCommandSave =
          AppThingCommandSave.builder()
              .thingNbr(sent)
              .description("desc")
              .fullDescription("fullDesc")
              .comments(Arrays.asList("Larry", "Moe", "Curly"))
              .parts(appParts)
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
    List<ThingComment> comments = thingEntity.getComments();
    assertTrue(comments.size() == 3);
    comments.forEach(s -> System.out.println(s.toString()));
    List<ThingPart> thingParts = thingEntity.getParts();
    assertTrue(thingParts.size() == 3);
    thingParts.forEach(s -> System.out.println(s.toString()));
  }
}
