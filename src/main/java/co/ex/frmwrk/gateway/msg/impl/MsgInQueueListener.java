package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.event.jpa.EventEntity;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.EventRepository;
import co.ex.frmwrk.mapping.ThingDtoSaveToEventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MsgInQueueListener {
  private final EventRepository eventRepository;

  @JmsListener(destination = JmsConfig.EVENT_Q)
  public void listen(@Payload ThingDtoSave thingDtoSave, @Headers MessageHeaders messageHeaders) {

    System.out.println("Message id from topic " + thingDtoSave.getThingNbr());
    EventEntity eventEntity =
        ThingDtoSaveToEventEntity.INSTANCE.thingDtoSaveToThingEntity(thingDtoSave);
    EventEntity eventEntityRtn = eventRepository.save(eventEntity);
    System.out.println(eventEntityRtn.toString());
  }
}
