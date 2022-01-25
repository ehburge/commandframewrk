package co.ex.frmwrk.gateway.jpa.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateThingEntityCommandTest {

  @LocalServerPort private int port;

  @Autowired ThingDtoSaveToThingEntity thingDtoSaveToThingEntity;
  @Autowired private ThingEntityRepository thingRepository;

  @Test
  public void createThingCommand() {

    ThingDtoComments thingDtoComments =
        ThingDtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();

    ThingDtoSave thingDtoSave =
        ThingDtoSave.builder()
            .thingNbr(10123L)
            .comments(thingDtoComments)
            .parts(makeAppThingParts())
            .build();

    ThingEntity thingEntity = thingDtoSaveToThingEntity.thingDtoSaveToThingEntity(thingDtoSave);

    thingRepository.save(thingEntity);

    ThingEntity saveThingEntity = thingRepository.findDistinctByThingNbr(10123L);
    assertEquals(10123L, saveThingEntity.getThingNbr());
  }

  ThingDtoParts makeAppThingParts() {
    ThingDtoPart thingPart1 = ThingDtoPart.builder().partId("id1-part").qty((2)).build();
    ThingDtoPart thingPart2 = ThingDtoPart.builder().partId("id2-part").qty(3).build();
    ThingDtoPart thingPart3 = ThingDtoPart.builder().partId("id3-part").qty(2).build();

    List<ThingDtoPart> thingParts = new ArrayList<>();
    thingParts.add(thingPart1);
    thingParts.add(thingPart2);
    thingParts.add(thingPart3);

    return ThingDtoParts.builder().parts(thingParts).build();
  }
}
