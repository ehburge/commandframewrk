package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.framewrk.eventer.model.ThingDtoSaveEvent;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenToEventer {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenToEventer.class);
  private final JmsMessagingTemplate jmsTemplate;
  private final ThingDtoSave_EventMapper dtoSaveEventMapper;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(String json) {
    ThingDtoSave thingDtoSave = co.ex.eventer.JsonMapper.fromJson(json, ThingDtoSave.class);
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    ThingDtoSaveEvent thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave);
    String sendJson = JsonMapper.toJson(thingDtoEvent);
    LOGGER.debug(
        ("TopicListenToEventer.topicListen() - " + ThingDtoSaveEvent.class.getSimpleName())
            .concat(System.lineSeparator())
            .concat(sendJson));

    jmsTemplate.convertAndSend("anycast://".concat(JmsConfig.EVENT_Q), sendJson);
  }
}
