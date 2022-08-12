package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.eventer.command.Constants;
import co.ex.framewrk.eventer.model.impl000.ThingDtoSaveEvent000;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import jakarta.jms.Message;
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

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(@Payload String json) {
    ThingDtoSave thingDtoSave = JsonMapper.fromJson(json, ThingDtoSave.class);
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    ThingDtoSaveEvent000 thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave);
    String sendJson = JsonMapper.toJson(thingDtoEvent);
    LOGGER.debug(
        ("TopicListenToEventer.topicListen() - " + ThingDtoSaveEvent000.class.getSimpleName())
            .concat(System.lineSeparator())
            .concat(sendJson));

    jmsTemplate.convertAndSend("anycast://".concat(JmsConfig.EVENT_Q), sendJson, m -> {
      m.setStringProperty(Constants.CLASS_NAME, thingDtoEvent.getClass().getName());
      return m;
    });
  }
}
