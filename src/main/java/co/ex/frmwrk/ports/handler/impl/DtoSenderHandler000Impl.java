package co.ex.frmwrk.ports.handler.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.frmin.persist_incoming.ThingIncomingThingNbrSeq;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.ports.handler.DtoSenderHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DtoSenderHandler000Impl implements DtoSenderHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DtoSenderHandler000Impl.class);


  @Qualifier("jmsTemplateMulticast")
  private final JmsTemplate jmsTemplateMulticast;

  private final ThingIncomingThingNbrSeq thingNbrSeq;

  @Override
  public void handle(ThingDto thingDto) {

    ThingDtoSave000 thingDtoSave000 = (ThingDtoSave000) thingDto;
    LOGGER.debug(
        "DtoSenderHandlerImpl.handle()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingDtoSave000)));

    // String json = co.ex.eventer.JsonMapper.toJson(thingDtoSave);
    jmsTemplateMulticast.convertAndSend(
        "multicast://".concat(JmsConfig.SEND_LISTEN_TOPIC), thingDtoSave000);
  }
}
