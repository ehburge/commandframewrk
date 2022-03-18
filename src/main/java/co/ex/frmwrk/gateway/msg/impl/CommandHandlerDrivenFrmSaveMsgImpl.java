package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.topic.impl.TopicSendListen;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommandHandlerDrivenFrmSaveMsgImpl
    implements CommandHandlerDrivenFrm<ThingDtoSave>,
        co.ex.frmwrk.gateway.msg.CommandHandlerDrivenFrmSaveMsg {
  Logger LOGGER = LoggerFactory.getLogger( CommandHandlerDrivenFrmSaveMsgImpl.class );

  private final TopicSendListen topicSendListen;

  @Override
  public void handle(ThingDtoSave thingDtoSave) {
    LOGGER.debug(
            "CommandHandlerDrivenFrmSaveMsgImpl.handle()".concat(System.lineSeparator()),
            JsonMapper.toJson(thingDtoSave));

    topicSendListen.sendThingDto(thingDtoSave);
  }
}
