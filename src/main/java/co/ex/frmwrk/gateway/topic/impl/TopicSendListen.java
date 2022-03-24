package co.ex.frmwrk.gateway.topic.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntityThingNbrSeq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TopicSendListen {
  private static Logger LOGGER = LoggerFactory.getLogger(TopicSendListen.class);
  private final JmsTemplate jmsTemplate;
  private final ThingEntityThingNbrSeq thingNbrSeq;
  public TopicSendListen(
      @Qualifier("jmsTemplate") JmsTemplate jmsTemplate,
      ThingEntityThingNbrSeq thingNbrSeq) {
    this.jmsTemplate = jmsTemplate;
    this.thingNbrSeq = thingNbrSeq;
  }

  public void sendThingDto(ThingDtoSave thingDtoSave) {

    thingNbrSeq.setThingNbrWhenNull(thingDtoSave);
    LOGGER.debug(
        "TopicSendListen.sendThingDto()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    String json = co.ex.eventer.event.JsonMapper.toJson(thingDtoSave);
    jmsTemplate.convertAndSend("multicast://VirtualTopic.send-listen", json);
    // jmsTopicTemplate.convertAndSend("send.persist.topic", thingDtoSave);
  }
}
