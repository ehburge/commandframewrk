package co.ex.frmwrk.frmin.rest.impl;

import co.ex.eventer.JsonMapper;
import co.ex.frmwrk.config.JsonMapper;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import co.ex.frmwrk.gateway.impl.DtoRole;
import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoPart;
import co.ex.frmwrk.gateway.impl.DtoParts;
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

    DtoComments comments =
        DtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    DtoParts evThingParts = makeThingParts();
    int evPartsSize = evThingParts.getParts().size();
    DtoRole evThingRole = new DtoRole(1L, "grpId", "loyalty");
    FrmInCommandSave commandSave =
        FrmInCommandSave.builder()
            .thingNbr(1L)
            .role(evThingRole)
            .eventKind("CREATED")
            .comments(comments)
            .parts(makeThingParts())
            .build();
    System.out.println(JsonMapper.toJson(commandSave));
  }

  DtoParts makeThingParts() {
    DtoPart thingPart1 =
        DtoPart.builder().partId("id1-part").qty(2).lineAction("ADDED").build();
    DtoPart thingPart2 =
        DtoPart.builder().partId("id2-part").qty(3).lineAction("ADDED").build();
    DtoPart thingPart3 =
        DtoPart.builder().partId("id3-part").qty(2).lineAction("ADDED").build();

    List<DtoPart> thingParts = new ArrayList<>();
    thingParts.add(thingPart1);
    thingParts.add(thingPart2);
    thingParts.add(thingPart3);

    return DtoParts.builder().parts(thingParts).build();
  }
}
