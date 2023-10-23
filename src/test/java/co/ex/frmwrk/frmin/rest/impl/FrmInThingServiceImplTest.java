package co.ex.frmwrk.frmin.rest.impl;

import co.ex.eventer.JsonMapper;
import co.ex.frmwrk.frmin.cmd.impl.FrmInThingCommand000;
import co.ex.frmwrk.gateway.impl.DtoRole;
import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrmInThingServiceImplTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void perform() {

    ThingDtoComments comments =
        ThingDtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    ThingDtoParts evThingParts = makeThingParts();
    int evPartsSize = evThingParts.getParts().size();
    DtoRole evThingRole = new DtoRole(1L, "grpId", "loyalty");
    FrmInThingCommand000 commandSave =
        FrmInThingCommand000.builder()
            .thingNbr(1L)
            .role(evThingRole)
            .eventKind("CREATED")
            .comments(comments)
            .parts(makeThingParts())
            .build();
    System.out.println(JsonMapper.toJson(commandSave));
  }

  ThingDtoParts makeThingParts() {
    ThingDtoPart thingPart1 =
        ThingDtoPart.builder().partId("id1-part").qty(2).lineAction("ADDED").build();
    ThingDtoPart thingPart2 =
        ThingDtoPart.builder().partId("id2-part").qty(3).lineAction("ADDED").build();
    ThingDtoPart thingPart3 =
        ThingDtoPart.builder().partId("id3-part").qty(2).lineAction("ADDED").build();

    List<ThingDtoPart> thingParts = new ArrayList<>();
    thingParts.add(thingPart1);
    thingParts.add(thingPart2);
    thingParts.add(thingPart3);

    return ThingDtoParts.builder().parts(thingParts).build();
  }
}
