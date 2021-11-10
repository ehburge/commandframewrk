package co.ex.frmwrk.gateway.jpa.impl;

import cmd.impl.AppThingCommandSave;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.gateway.jpa.ThingEntity;
import co.ex.frmwrk.gateway.jpa.ThingRepository;
import co.ex.frmwrk.gateway.msg.impl.PersistListener;
import org.apache.activemq.artemis.core.server.QueueQueryResult;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    // classes = ThingIntegTestApp.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppCommandSaveMsgTest {

  @LocalServerPort private int port;

  @Autowired private CommandBusDrivingApp commandBusDrivingApp;

  @Autowired private ThingRepository thingRepository;

  @Autowired private EmbeddedActiveMQ embeddedActiveMQ;

  @Autowired private PersistListener persistListener;

  @Test
  public void testCreateThingCommand() {

    AppThingCommandSave appThingCommandSave1 =
        AppThingCommandSave.builder()
            .thingNbr(123L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("123.45"))
            .build();
    AppThingCommandSave appThingCommandSave2 =
        AppThingCommandSave.builder()
            .thingNbr(234L)
            .description("desc")
            .fullDescription("fullDesc")
            .price(new BigDecimal("234.56"))
            .build();
    AppThingCommandSave appThingCommandSave3 =
        AppThingCommandSave.builder()
            .thingNbr(345L)
            .description("desc")
            .fullDescription("fullDesc")
            .price(new BigDecimal("345.67"))
            .build();
    AppThingCommandSave appThingCommandSave4 =
        AppThingCommandSave.builder()
            .thingNbr(456L)
            .description("desc")
            .fullDescription("fullDesc")
            .price(new BigDecimal("456.78"))
            .build();

    int msgCount = 0;

    Long t = System.currentTimeMillis();
    commandBusDrivingApp.perform(appThingCommandSave1);
    Long t1 = System.currentTimeMillis();
    System.out.println("first time " + (t1 - t));
    commandBusDrivingApp.perform(appThingCommandSave2);
    Long t2 = System.currentTimeMillis();
    System.out.println("second time " + (t2 - t1));
    commandBusDrivingApp.perform(appThingCommandSave3);
    Long t3 = System.currentTimeMillis();
    System.out.println("third time " + (t3 - t2));
    commandBusDrivingApp.perform(appThingCommandSave4);
    Long t4 = System.currentTimeMillis();
    System.out.println("fourth time " + (t4 - t3));
    System.out.println("total time " + (t4 - t));

    QueueQueryResult queueQueryResult;

    while (persistListener.getNbrMsgs() < 4) {
      System.out.println("nbrMsgs=".concat(String.valueOf(persistListener.getNbrMsgs())));
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
    }

    ThingEntity thingEntity = thingRepository.findDistinctByThingNbr(234L);
    assertEquals(234L, thingEntity.getThingNbr());
  }
}
