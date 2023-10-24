package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.framewrk.eventer.model.impl.DtoSaveEventImpl;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
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
  private static final String CLASS_NAME_STRING = "className";
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenSendToEventer.class);

  @Qualifier("jmsTemplateAnycast")
  private final JmsTemplate jmsTemplateAnycast;

  private final CommandDtoSave_EventMapper dtoSaveEventMapper;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(@Payload DtoCommandSave dtoCommandSave) {
    LOGGER.debug(
        "TopicListenToEventer.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(dtoCommandSave)));

    DtoSaveEventImpl dtoSaveEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(dtoCommandSave);
    String sendJson = JsonMapper.toJson(dtoSaveEvent);
    LOGGER.debug(
        ("TopicListenToEventer.topicListen() - " + DtoSaveEventImpl.class.getSimpleName())
            .concat(System.lineSeparator())
            .concat(sendJson));

    jmsTemplateAnycast.convertAndSend(
        "anycast://".concat(JmsConfig.EVENT_Q),
            dtoSaveEvent,
        m -> {
          m.setStringProperty(CLASS_NAME_STRING, dtoSaveEvent.getClass().getName());
          return m;
        });
  }
}
