package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.impl.ThingEntityThingNbrSeqPostgres;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicSendListen {
  private static Logger LOGGER = LoggerFactory.getLogger(TopicSendListen.class);

  private final JmsTemplate jmsTopicTemplate;
  private final ThingEntityThingNbrSeqPostgres thingNbrSeq;

  public void sendThingDto(ThingDtoSave thingDtoSave) {

    thingNbrSeq.setThingNbrWhenNull(thingDtoSave);
    LOGGER.debug(
        "TopicSendListen.sendThingDto()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    jmsTopicTemplate.convertAndSend(new ActiveMQTopic(JmsConfig.SEND_LISTEN_TOPIC), thingDtoSave);
  }
}
