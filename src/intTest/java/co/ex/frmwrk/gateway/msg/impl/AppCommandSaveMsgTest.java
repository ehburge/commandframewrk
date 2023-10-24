package co.ex.frmwrk.gateway.msg.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import co.ex.app.cmd.impl.AppCommandCommand000;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.app.model.AppCommandComments;
import co.ex.app.model.AppCommandPart;
import co.ex.app.model.AppCommandParts;
import co.ex.frmwrk.frmin.persist_incoming.CommandIncomingIdSeq;
import co.ex.frmwrk.gateway.persist.CommandEntity;
import co.ex.frmwrk.gateway.persist.CommandEntityRepository;
import java.beans.PropertyChangeListener;
import java.util.*;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppCommandSaveMsgTest {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  @Autowired AppSetupMapBeans appSetupMapBeans;
  @LocalServerPort private int port;
  private CommandBusDrivingApp commandBusDrivingApp;

  @Autowired private CommandEntityRepository commandRepository;

  @Autowired private EmbeddedActiveMQ embeddedActiveMQ;

  @Autowired private CommandHandlerDrivenFrmSaveMsgListener commandHandlerDrivenFrmSaveMsgListener;

  @Autowired private EventQueueListener eventQueueListener;

  @Autowired
  CommandIncomingIdSeq commandIncomingIdSeq;

  @Test
  @Transactional
  public void testCreateCommandCommandVolume() throws Exception {

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

    AppCommandPart commandPart1 = AppCommandPart.builder().partId("1").qty(1).build();
    AppCommandPart commandPart2 = AppCommandPart.builder().partId("2").qty(2).build();
    AppCommandPart commandPart3 = AppCommandPart.builder().partId("3").qty(3).build();

    List<AppCommandPart> appParts =
        new ArrayList<>(Arrays.asList(commandPart1, commandPart2, commandPart3));
    AppCommandParts commandParts = AppCommandParts.builder().parts(appParts).build();

    AppCommandComments commandComments =
        AppCommandComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();

    long strt = System.currentTimeMillis();

    for (int i = 0; i < nbrMsgs; i++) {
      AppCommandCommand000 appCommandCommandV1 =
          AppCommandCommand000.builder()
              .commandNbr(commandIncomingIdSeq.setCommandNbrWhenNull(10L))
              .comments(commandComments)
              .parts(commandParts)
              .build();
      commandBusDrivingApp.perform(appCommandCommandV1);
      sent++;
    }

    System.out.println("loop".concat(Long.toString(System.currentTimeMillis() - strt)));

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
    System.out.println("lock ".concat(Long.toString(System.currentTimeMillis() - strt)));

    System.out.println("start listing");
    List<CommandEntity> commandEntities = commandRepository.findByCommandNbrOrderByDttm(10L);
    assertEquals(nbrMsgs, commandEntities.size());
    commandEntities.stream()
        .forEach(
            e -> {
              System.out.println("*** " + e.toString());
            });
    System.out.println("end listing");

    System.out.println((System.currentTimeMillis() - strt));
  }
}
