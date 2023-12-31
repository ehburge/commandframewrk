package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.Dto;
import co.ex.frmwrk.gateway.msg.CommandHandlerDrivenFrmSaveMsg;
import co.ex.frmwrk.send.bus.DtoSenderBus;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommandHandlerDrivenFrmSaveMsgImpl
    implements CommandHandlerDrivenFrm, CommandHandlerDrivenFrmSaveMsg {

  private final Map<Class<? extends Dto>, DtoSenderBus> cbMap;

  Logger LOGGER = LoggerFactory.getLogger(CommandHandlerDrivenFrmSaveMsgImpl.class);

  @Override
  public void handle(Dto dto) {
    LOGGER.debug(
        "CommandHandlerDrivenFrmSaveMsgImpl.handle()".concat(System.lineSeparator()),
        JsonMapper.toJson(dto));

    cbMap.get(dto.getClass()).perform(dto);
  }
}
