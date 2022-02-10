package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.eventer.EventCrtUpd;
import co.ex.frmwrk.eventer.model.impl.ThingComments;
import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.ex.thing.model.AppThingComments;
import com.ex.thing.model.AppThingPart;
import com.ex.thing.model.AppThingParts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicSendListenTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Inject TopicSendListen topicSendListen;

  @Test
  void sendThingDto() {
    ThingDtoSave thingDtoSave = ThingDtoSave.builder().thingNbr(12L).comments(thingComments).parts(thingParts).eventKind(EventCrtUpd.CREATED.name()).build();
    System.out.println("*** Sending ThingDtoSave");
    topicSendListen.sendThingDto(thingDtoSave);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  ThingDtoPart thingPart1 = ThingDtoPart.builder().partId("1").qty(1).build();
  ThingDtoPart thingPart2 = ThingDtoPart.builder().partId("2").qty(2).build();
  ThingDtoPart thingPart3 = ThingDtoPart.builder().partId("3").qty(3).build();

  List<ThingDtoPart> dtoParts =
      new ArrayList<>(Arrays.asList(thingPart1, thingPart2, thingPart3));
  ThingDtoParts thingParts = ThingDtoParts.builder().parts(dtoParts).build();

  ThingDtoComments thingComments =
      ThingDtoComments.builder().comments(Arrays.asList("Larry", "Moe", "Curly")).build();


}