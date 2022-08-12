package co.ex.frmwrk.gateway.ports.handler.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntityThingNbrSeq;
import co.ex.frmwrk.gateway.ports.handler.DtoSenderHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DtoSenderHandlerImpl implements DtoSenderHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DtoSenderHandlerImpl.class);
  private final JmsTemplate jmsTemplate;
  private final ThingEntityThingNbrSeq thingNbrSeq;

  @Override
  public void handle(ThingDto thingDto) {

    ThingDtoSave thingDtoSave = (ThingDtoSave) thingDto;
    thingNbrSeq.setThingNbrWhenNull(thingDtoSave);
    LOGGER.debug(
        "DtoSenderHandlerImpl.handle()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave)));

    String json = co.ex.eventer.JsonMapper.toJson(thingDtoSave);
    jmsTemplate.convertAndSend("multicast://".concat(JmsConfig.SEND_LISTEN_TOPIC), json);
  }
}
