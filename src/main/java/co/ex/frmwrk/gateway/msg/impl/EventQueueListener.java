package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.persist.EventEntity;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.EventRepository;
import co.ex.frmwrk.mapping.ThingDtoSaveToEventEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventQueueListener {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final EventRepository eventRepository;

  @JmsListener(destination = JmsConfig.EVENT_Q)
  public void listen(@Payload ThingDtoSave thingDtoSave, @Headers MessageHeaders messageHeaders) {

    EventEntity eventEntity =
        ThingDtoSaveToEventEntity.INSTANCE.thingDtoSaveToThingEntity(thingDtoSave);
    EventEntity eventEntityRtn = eventRepository.save(eventEntity);
    LOGGER.debug("EventEntity saved " + eventEntityRtn.toString());
  }
}
