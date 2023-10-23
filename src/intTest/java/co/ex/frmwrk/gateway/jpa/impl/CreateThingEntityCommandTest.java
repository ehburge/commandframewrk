package co.ex.frmwrk.gateway.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoPart;
import co.ex.frmwrk.gateway.impl.DtoParts;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingEntityRepository;
import co.ex.frmwrk.frmin.persist_incoming.impl.ThingIncomingThingNbrSeqPostgres;
import co.ex.frmwrk.mapping.DtoCommandSave_ThingEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  @Autowired private DtoCommandSave_ThingEntity dtoCommandSave_thingEntity;
  @Autowired private ThingIncomingThingNbrSeqPostgres thingNbrSeq;

  @Test
  public void createThingCommand() {
    thingRepository.deleteAll();

    DtoComments dtoComments =
        DtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    for (int o = 0; o < 3; o++) {
      for (int i = 0; i < 5; i++) {
        DtoCommandSave dtoCommandSave =
            DtoCommandSave.builder()
                .thingNbr(thingNbrSeq.setThingNbrWhenNull(null))
                .comments(dtoComments)
                .parts(makeAppThingParts())
                .build();


        ThingEntity thingEntity = dtoCommandSave_thingEntity.thingDtoSaveToThingEntity(dtoCommandSave);

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

  DtoParts makeAppThingParts() {
    DtoPart thingPart1 = DtoPart.builder().partId("id1-part").qty((2)).build();
    DtoPart thingPart2 = DtoPart.builder().partId("id2-part").qty(3).build();
    DtoPart thingPart3 = DtoPart.builder().partId("id3-part").qty(2).build();

    List<DtoPart> thingParts = new ArrayList<>();
    thingParts.add(thingPart1);
    thingParts.add(thingPart2);
    thingParts.add(thingPart3);

    return DtoParts.builder().parts(thingParts).build();
  }
}
