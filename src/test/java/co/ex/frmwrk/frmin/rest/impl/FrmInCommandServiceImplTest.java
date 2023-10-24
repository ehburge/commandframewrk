package co.ex.frmwrk.frmin.rest.impl;

import co.ex.frmwrk.config.JsonMapper;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoPart;
import co.ex.frmwrk.gateway.impl.DtoParts;
import co.ex.frmwrk.gateway.impl.DtoRole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrmInCommandServiceImplTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void perform() {

    DtoComments comments =
        DtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    DtoParts evCommandParts = makeCommandParts();
    int evPartsSize = evCommandParts.getParts().size();
    DtoRole evCommandRole = new DtoRole(1L, "grpId", "loyalty");
    FrmInCommandSave commandSave =
        FrmInCommandSave.builder()
            .commandNbr(1L)
            .role(evCommandRole)
            .eventKind("CREATED")
            .comments(comments)
            .parts(makeCommandParts())
            .build();
    System.out.println(JsonMapper.toJson(commandSave));
  }

  DtoParts makeCommandParts() {
    DtoPart commandPart1 = DtoPart.builder().partId("id1-part").qty(2).lineAction("ADDED").build();
    DtoPart commandPart2 = DtoPart.builder().partId("id2-part").qty(3).lineAction("ADDED").build();
    DtoPart commandPart3 = DtoPart.builder().partId("id3-part").qty(2).lineAction("ADDED").build();

    List<DtoPart> commandParts = new ArrayList<>();
    commandParts.add(commandPart1);
    commandParts.add(commandPart2);
    commandParts.add(commandPart3);

    return DtoParts.builder().parts(commandParts).build();
  }
}
