package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.topic.impl.TopicSendListen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommandHandlerDrivenFrmSaveMsgImpl
    implements CommandHandlerDrivenFrm<ThingDtoSave>,
        co.ex.frmwrk.gateway.msg.CommandHandlerDrivenFrmSaveMsg {

  private final TopicSendListen topicSendListen;

  @Override
  public void handle(ThingDtoSave thingDtoSave) {
    topicSendListen.sendThingDto(thingDtoSave);
  }
}
