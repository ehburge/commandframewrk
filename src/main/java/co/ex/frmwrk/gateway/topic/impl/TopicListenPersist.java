package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.eventer.json.JsonMapper;
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

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(ThingDtoSave thingDtoSave) {
    LOGGER.info("*** Persistng ThingDtoSave\n" + JsonMapper.toJson(thingDtoSave));
    persistThingDto.persist(thingDtoSave);
  }
}
