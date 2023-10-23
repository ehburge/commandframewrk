package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
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

  private final Map<Class<? extends ThingDto>, DtoSenderBus> cbMap;

  Logger LOGGER = LoggerFactory.getLogger(CommandHandlerDrivenFrmSaveMsgImpl.class);

  @Override
  public void handle(ThingDto thingDto) {
    LOGGER.debug(
        "CommandHandlerDrivenFrmSaveMsgImpl.handle()".concat(System.lineSeparator()),
        JsonMapper.toJson(thingDto));

    cbMap.get(thingDto.getClass()).perform(thingDto);
  }
}
