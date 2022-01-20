package co.ex.frmwrk.gateway.jpa.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.mapping.ThingDtoSaveToThingEntity;
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
public class CreateThingEntityCommandTest {

  @LocalServerPort private int port;

  @Autowired private ThingEntityRepository thingRepository;

  // @Autowired private ThingCommandBusPrimary thingCommandBusPrimary;

  @Test
  public void createThingCommand() {
    ThingDtoSave thingDtoSave =
        ThingDtoSave.builder()
            .thingNbr(10123L)
            .description("desc")
            .fullDescription("full desc")
            //.price(new BigDecimal("123.45")
            .build();

//    ThingEntity thingEntity =
//        ThingDtoSaveToThingEntity.thingDtoSaveToThingEntity(thingDtoSave);

    //thingRepository.save(thingEntity);

    ThingEntity savdThingEntity = thingRepository.findDistinctByThingNbr(10123L);
    assertEquals(10123L, savdThingEntity.getThingNbr());
  }
}
