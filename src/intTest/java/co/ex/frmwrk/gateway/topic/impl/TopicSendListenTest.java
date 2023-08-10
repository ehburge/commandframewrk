package co.ex.frmwrk.gateway.topic.impl;

import co.ex.share.event.KindOfEvent;
import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.gateway.msg.impl.EventQueueListener;
import co.ex.frmwrk.ports.bus.DtoSenderBus;
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

  ThingDtoPart thingPart1 = ThingDtoPart.builder().partId("1").qty(1).build();
  ThingDtoPart thingPart2 = ThingDtoPart.builder().partId("2").qty(2).build();
  ThingDtoPart thingPart3 = ThingDtoPart.builder().partId("3").qty(3).build();
  List<ThingDtoPart> dtoParts = new ArrayList<>(Arrays.asList(thingPart1, thingPart2, thingPart3));
  ThingDtoParts thingParts = ThingDtoParts.builder().parts(dtoParts).build();
  ThingDtoComments thingComments =
      ThingDtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void sendThingDto() {
    System.out.println("*** Sending ThingDtoSave");
    for (int i = 0; i < 30; i++) {
      ThingDtoSave000 thingDtoSave000 =
          ThingDtoSave000.builder()
              .thingNbr(Long.valueOf(i))
              .comments(thingComments)
              .parts(thingParts)
              .eventKind(KindOfEvent.CREATED.name())
              .build();

      cbMap.get(thingDtoSave000.getClass()).perform(thingDtoSave000);
    }
  }
}
