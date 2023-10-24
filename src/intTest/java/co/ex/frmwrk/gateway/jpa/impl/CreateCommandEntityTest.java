package co.ex.frmwrk.gateway.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import co.ex.frmwrk.frmin.persist_incoming.impl.CommandIncomingCommandNbrSeqPostgres;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoPart;
import co.ex.frmwrk.gateway.impl.DtoParts;
import co.ex.frmwrk.gateway.persist.CommandEntity;
import co.ex.frmwrk.gateway.persist.CommandEntityRepository;
import co.ex.frmwrk.mapping.DtoCommandSave_CommandEntity;
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
public class CreateCommandEntityTest {

  @LocalServerPort private int port;

  @Autowired private CommandEntityRepository commandRepository;
  @Autowired private DtoCommandSave_CommandEntity dtoCommandSave_commandEntity;
  @Autowired private CommandIncomingCommandNbrSeqPostgres commandNbrSeq;

  @Test
  public void createCommandCommand() {
    commandRepository.deleteAll();

    DtoComments dtoComments =
        DtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();
    for (int o = 0; o < 3; o++) {
      for (int i = 0; i < 5; i++) {
        DtoCommandSave dtoCommandSave =
            DtoCommandSave.builder()
                .id(commandNbrSeq.setCommandNbrWhenNull(null))
                .comments(dtoComments)
                .parts(makeAppCommandParts())
                .build();


        CommandEntity commandEntity = dtoCommandSave_commandEntity.commandDtoSaveToCommandEntity(dtoCommandSave);

        CommandEntity commandEntityRtn = commandRepository.save(commandEntity);

        List<CommandEntity> saveCommandEntity =
            commandRepository.findByCommandNbrOrderByDttm(commandEntityRtn.getCommandNbr());
        assertEquals(commandEntityRtn.getCommandNbr(), saveCommandEntity.get(0).getCommandNbr());
      }
    }
  }
  //  public Long getUid() {
  //    PostgreSQLSequenceMaxValueIncrementer uidIncrementer =
  //        (PostgreSQLSequenceMaxValueIncrementer)context.getBean("uidIncrementer");
  //    return uidIncrementer .nextLongValue();
  //  }

  DtoParts makeAppCommandParts() {
    DtoPart commandPart1 = DtoPart.builder().partId("id1-part").qty((2)).build();
    DtoPart commandPart2 = DtoPart.builder().partId("id2-part").qty(3).build();
    DtoPart commandPart3 = DtoPart.builder().partId("id3-part").qty(2).build();

    List<DtoPart> commandParts = new ArrayList<>();
    commandParts.add(commandPart1);
    commandParts.add(commandPart2);
    commandParts.add(commandPart3);

    return DtoParts.builder().parts(commandParts).build();
  }
}
