package co.ex.frmwrk.gateway.jpa.impl;

import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.domain.cmd.impl.CreateThingCommand;
import co.ex.domain.cmd.impl.SaveThingCommand;
import co.ex.frmwrk.gateway.jpa.ThingEntity;
import co.ex.frmwrk.gateway.jpa.ThingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaveThingCommandBusTest {

  @LocalServerPort private int port;

  @Autowired private ThingRepository thingRepository;

  @Autowired private CommandBusDrivingApp commandBusDrivingApp;

  @Test
  public void testCreateThingCommand() {

    CreateThingCommand createThingCommand1 =
        CreateThingCommand.builder()
            .thingNbr(123L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("123.45"))
            .build();
    CreateThingCommand createThingCommand2 =
        CreateThingCommand.builder()
            .thingNbr(234L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("234.56"))
            .build();
    CreateThingCommand createThingCommand3 =
        CreateThingCommand.builder()
            .thingNbr(345L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("345.67"))
            .build();
    CreateThingCommand createThingCommand4 =
        CreateThingCommand.builder()
            .thingNbr(456L)
            .description("desc")
            .fullDescription("fillDesc")
            .price(new BigDecimal("456.78"))
            .build();

    Long t = System.currentTimeMillis();
    commandBusDrivingApp.execute(createThingCommand1);
    Long t1 = System.currentTimeMillis();
    System.out.println("first time " + (t1 - t));
    commandBusDrivingApp.execute(createThingCommand2);
    Long t2 = System.currentTimeMillis();
    System.out.println("second time " + (t2 - t1));
    commandBusDrivingApp.execute(createThingCommand3);
    Long t3 = System.currentTimeMillis();
    System.out.println("third time " + (t3 - t2));
    commandBusDrivingApp.execute(createThingCommand4);
    Long t4 = System.currentTimeMillis();
    System.out.println("fourth time " + (t4 - t3));
    System.out.println("total time " + (t4 - t));

    SaveThingCommand saveThingCommand1 =
        SaveThingCommand.builder()
            .thingNbr(123L)
            .description("desc saved123")
            .fullDescription("fillDesc")
            .price(new BigDecimal("123.45"))
            .build();
    SaveThingCommand saveThingCommand2 =
        SaveThingCommand.builder()
            .thingNbr(234L)
            .description("desc saved234")
            .fullDescription("fillDesc")
            .price(new BigDecimal("234.56"))
            .build();
    SaveThingCommand saveThingCommand3 =
        SaveThingCommand.builder()
            .thingNbr(345L)
            .description("desc saved345")
            .fullDescription("fillDesc")
            .price(new BigDecimal("345.67"))
            .build();
    SaveThingCommand saveThingCommand4 =
        SaveThingCommand.builder()
            .thingNbr(456L)
            .description("desc saved456")
            .fullDescription("fillDesc")
            .price(new BigDecimal("456.78"))
            .build();

    System.out.println();
    System.out.println("Saving");
    System.out.println();

    Long ts = System.currentTimeMillis();
    commandBusDrivingApp.execute(saveThingCommand1);
    Long ts1 = System.currentTimeMillis();
    System.out.println("first time " + (ts1 - ts));
    commandBusDrivingApp.execute(saveThingCommand2);
    Long ts2 = System.currentTimeMillis();
    System.out.println("second time " + (ts2 - ts1));
    commandBusDrivingApp.execute(saveThingCommand3);
    Long ts3 = System.currentTimeMillis();
    System.out.println("third time " + (ts3 - ts2));
    commandBusDrivingApp.execute(saveThingCommand4);
    Long ts4 = System.currentTimeMillis();
    System.out.println("fourth time " + (ts4 - ts3));
    System.out.println("total time " + (ts4 - ts));

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ThingEntity thingEntity = thingRepository.findDistinctByThingNbr(345L);
    assertEquals("desc saved345", thingEntity.getDescription());
  }
}
