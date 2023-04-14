package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.eventer.command.Constants;
import co.ex.framewrk.eventer.model.impl000.ThingDtoSaveEvent000;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenSendToEventer {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenSendToEventer.class);

  @Qualifier("jmsTemplateAnycast")
  private final JmsTemplate jmsTemplateAnycast;

  private final ThingDtoSave_EventMapper dtoSaveEventMapper;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(@Payload ThingDtoSave000 thingDtoSave000) {
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave000)));

    ThingDtoSaveEvent000 thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave000);
    String sendJson = JsonMapper.toJson(thingDtoEvent);
    LOGGER.debug(
        ("TopicListenToEventer.topicListen() - " + ThingDtoSaveEvent000.class.getSimpleName())
            .concat(System.lineSeparator())
            .concat(sendJson));

    jmsTemplateAnycast.convertAndSend(
        "anycast://".concat(JmsConfig.EVENT_Q),
        thingDtoEvent,
        m -> {
          m.setStringProperty(Constants.CLASS_NAME, thingDtoEvent.getClass().getName());
          return m;
        });
  }
}
