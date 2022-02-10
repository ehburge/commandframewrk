package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicSendListen {

  private final JmsTemplate jmsTopicTemplate;

  public void sendThingDto(ThingDtoSave thingDtoSave) {
    jmsTopicTemplate.convertAndSend(new ActiveMQTopic(JmsConfig.SEND_LISTEN_TOPIC), thingDtoSave);
  }
}
