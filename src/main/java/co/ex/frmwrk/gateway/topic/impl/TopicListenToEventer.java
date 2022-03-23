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

  @JmsListener(destination = "VirtualTopic.send-listen")
  public void topicListen(ThingDtoSave thingDtoSave) {
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    ThingDtoSaveEvent thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave);
    LOGGER.debug(
        "TopicListenToEventer.topicListen() - ThingDtoSaveEvent"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoEvent)));

    jmsTemplate.convertAndSend(JmsConfig.EVENT_TOPIC, thingDtoEvent);
  }
}
