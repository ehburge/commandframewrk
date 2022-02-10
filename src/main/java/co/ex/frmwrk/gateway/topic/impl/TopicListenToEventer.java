package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.eventer.json.JsonMapper;
import co.ex.frmwrk.eventer.model.impl.ThingDtoEvent;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenToEventer {
  private static Logger LOGGER = LoggerFactory.getLogger(TopicListenToEventer.class);
  private final JmsTemplate jmsTemplate;
  private final ThingDtoSave_EventMapper dtoSaveEventMapper;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(ThingDtoSave thingDtoSave) {

    ThingDtoEvent thingDtoEvent = dtoSaveEventMapper.dtoSaveToDtoEvent(thingDtoSave);

    LOGGER.info("*** Sending ThingDtoEvent to Eventer\n" + JsonMapper.toJson(thingDtoEvent));
    jmsTemplate.convertAndSend(JmsConfig.EVENT_Q, thingDtoEvent);
  }
}
