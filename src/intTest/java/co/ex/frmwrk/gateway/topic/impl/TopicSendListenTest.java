package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app000.aggregate.event.KindOfEvent;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoPart;
import co.ex.frmwrk.gateway.impl.DtoParts;
import co.ex.frmwrk.gateway.msg.impl.EventQueueListener;
import co.ex.frmwrk.send.bus.DtoSenderBus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TopicSendListenTest {

  @Autowired Map<Class<?>, DtoSenderBus> cbMap;

  @Autowired EventQueueListener eventQueueListener;

  DtoPart commandPart1 = DtoPart.builder().partId("1").qty(1).build();
  DtoPart commandPart2 = DtoPart.builder().partId("2").qty(2).build();
  DtoPart commandPart3 = DtoPart.builder().partId("3").qty(3).build();
  List<DtoPart> dtoParts = new ArrayList<>(Arrays.asList(commandPart1, commandPart2, commandPart3));
  DtoParts commandParts = DtoParts.builder().parts(dtoParts).build();
  DtoComments commandComments =
      DtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void sendCommandDto() {
    System.out.println("*** Sending CommandDtoSave");
    for (int i = 0; i < 30; i++) {
      DtoCommandSave dtoCommandSave =
          DtoCommandSave.builder()
              .id(Long.valueOf(i))
              .comments(commandComments)
              .parts(commandParts)
              .eventKind(KindOfEvent.CREATED.name())
              .build();

      cbMap.get(dtoCommandSave.getClass()).perform(dtoCommandSave);
    }
  }
}
