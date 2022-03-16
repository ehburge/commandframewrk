package co.ex.frmwrk.gateway.msg.impl;

import co.ex.eventer.event.JsonMapper;
import co.ex.framewrk.eventer.model.ThingDtoSaveEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventQueueListener {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  //  @JmsListener(destination = JmsConfig.EVENT_Q)
  public void listen( ThingDtoSaveEvent thingDtoEvent) {

    LOGGER.info(
        "*** EventQueueListener ThingDtoEvent received " + JsonMapper.toJson(thingDtoEvent));
  }
}
