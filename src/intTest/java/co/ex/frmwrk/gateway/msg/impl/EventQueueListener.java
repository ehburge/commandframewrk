package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.eventer.json.JsonMapper;
import co.ex.frmwrk.eventer.model.impl.ThingDtoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventQueueListener {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  //  @JmsListener(destination = JmsConfig.EVENT_Q)
  public void listen(ThingDtoEvent thingDtoEvent) {

    LOGGER.info(
        "*** EventQueueListener ThingDtoEvent received " + JsonMapper.toJson(thingDtoEvent));
  }
}
