package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.config.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.impl.PersistCommandDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicListenPersist {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopicListenPersist.class);
  private final PersistCommandDto persistCommandDto;

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void topicListen(@Payload DtoCommandSave dtoCommandSave) {
    LOGGER.debug(
        "TopicListenPersist.topicListen()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(dtoCommandSave)));
    persistCommandDto.persist(dtoCommandSave);
  }
}
