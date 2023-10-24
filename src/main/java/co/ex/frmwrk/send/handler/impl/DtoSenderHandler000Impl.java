package co.ex.frmwrk.send.handler.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.frmin.persist_incoming.CommandIncomingIdSeq;
import co.ex.frmwrk.gateway.Dto;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.send.handler.DtoSenderHandler;
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

  private final CommandIncomingIdSeq commandNbrSeq;

  @Override
  public void handle(Dto dto) {

    DtoCommandSave dtoCommandSave = (DtoCommandSave) dto;
    LOGGER.debug(
        "DtoSenderHandlerImpl.handle()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(dtoCommandSave)));

    // String json = co.ex.eventer.JsonMapper.toJson(commandDtoSave);
    jmsTemplateMulticast.convertAndSend(
        "multicast://".concat(JmsConfig.SEND_LISTEN_TOPIC), dtoCommandSave);
  }
}
