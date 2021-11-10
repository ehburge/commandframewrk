package co.ex.frmwrk.gateway.jpa.impl;

import cmd.impl.AppThingCommandSave;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    // classes = ThingIntegTestApp.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DomainThingCommandSaveBusJPATest {

  @LocalServerPort private int port;

  @Autowired private CommandBusDrivingApp commandBusDrivingApp;

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
            .fullDescription("fillDesc")
            .price(new BigDecimal("234.56"))
            .build();
    AppThingCommandSave appThingCommandSave3 =
        AppThingCommandSave.builder()
            .thingNbr(345L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("345.67"))
            .build();
    AppThingCommandSave appThingCommandSave4 =
        AppThingCommandSave.builder()
            .thingNbr(456L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("456.78"))
            .build();

    Long t = System.currentTimeMillis();
    commandBusDrivingApp.perform(appThingCommandSave4);
    Long t1 = System.currentTimeMillis();
    System.out.println("first time " + (t1 - t));
    commandBusDrivingApp.perform(appThingCommandSave4);
    Long t2 = System.currentTimeMillis();
    System.out.println("second time " + (t2 - t1));
    commandBusDrivingApp.perform(appThingCommandSave4);
    Long t3 = System.currentTimeMillis();
    System.out.println("third time " + (t3 - t2));
    commandBusDrivingApp.perform(appThingCommandSave4);
    Long t4 = System.currentTimeMillis();
    System.out.println("fourth time " + (t4 - t3));
    System.out.println("total time " + (t4 - t));
  }
}
