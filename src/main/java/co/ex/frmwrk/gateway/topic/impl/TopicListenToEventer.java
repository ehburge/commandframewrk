package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.framewrk.eventer.model.ThingDtoSaveEvent;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenToEventer {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenToEventer.class);
  private final JmsTemplate jmsTemplate;
  private final ThingDtoSave_EventMapper dtoSaveEventMapper;
  private String thingDtoSaveEvent;

  @JmsListener(destination = "VirtualTopic.send-listen")
  public void topicListen(String json) {
    ThingDtoSave thingDtoSave = co.ex.eventer.event.JsonMapper.fromJson(json, ThingDtoSave.class);
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    ThingDtoSaveEvent thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave);
    String sendJson = JsonMapper.toJson(thingDtoEvent);
    thingDtoSaveEvent = "ThingDtoSaveEvent";
    LOGGER.debug(
        ("TopicListenToEventer.topicListen() - " + thingDtoSaveEvent)
            .concat(System.lineSeparator())
            .concat(sendJson));

    jmsTemplate.convertAndSend(JmsConfig.EVENT_Q, sendJson);
  }
}
