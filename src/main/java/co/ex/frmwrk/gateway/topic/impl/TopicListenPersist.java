package co.ex.frmwrk.gateway.topic.impl;

import co.ex.eventer.event.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.impl.PersistThingDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenPersist {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenPersist.class);
  private final PersistThingDto persistThingDto;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC, subscription = JmsConfig.SEND_LISTEN_TOPIC, id = "persist")
  public void topicListen(ThingDtoSave thingDtoSave) {
    LOGGER.debug(
            "TopicListenPersist.topicListen()"
                    .concat(System.lineSeparator())
                    .concat(JsonMapper.toJson(thingDtoSave)));
    persistThingDto.persist(thingDtoSave);
  }
}
