package co.ex.frmwrk.gateway.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.frmin.persist_incoming.impl.ThingIncomingThingNbrSeqPostgres;
import co.ex.frmwrk.mapping.ThingDtoSave_ThingEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateThingEntityCommandTest {

  @LocalServerPort private int port;

  @Autowired private ThingEntityRepository thingRepository;
  @Autowired private ThingDtoSave_ThingEntity thingDtoSave_thingEntity;
  @Autowired private ThingIncomingThingNbrSeqPostgres thingNbrSeq;

  @Test
  public void createThingCommand() {
    thingRepository.deleteAll();

    ThingDtoComments thingDtoComments =
        ThingDtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    for (int o = 0; o < 3; o++) {
      for (int i = 0; i < 5; i++) {
        ThingDtoSave000 thingDtoSave000 =
            ThingDtoSave000.builder()
                .thingNbr(thingNbrSeq.setThingNbrWhenNull(null))
                .comments(thingDtoComments)
                .parts(makeAppThingParts())
                .build();


        ThingEntity thingEntity = thingDtoSave_thingEntity.thingDtoSaveToThingEntity(thingDtoSave000);

        ThingEntity thingEntityRtn = thingRepository.save(thingEntity);

        List<ThingEntity> saveThingEntity =
            thingRepository.findByThingNbrOrderByDttm(thingEntityRtn.getThingNbr());
        assertEquals(thingEntityRtn.getThingNbr(), saveThingEntity.get(0).getThingNbr());
      }
    }
  }
  //  public Long getUid() {
  //    PostgreSQLSequenceMaxValueIncrementer uidIncrementer =
  //        (PostgreSQLSequenceMaxValueIncrementer)context.getBean("uidIncrementer");
  //    return uidIncrementer .nextLongValue();
  //  }

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
