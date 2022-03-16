package co.ex.frmwrk.gateway.msg.pipe.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class CommandHandlerDrivenFrmSavePipeMsgImpl
    implements CommandHandlerDrivenFrm<ThingDtoSave>,
        co.ex.frmwrk.gateway.msg.CommandHandlerDrivenFrmSaveMsg {

  private final JmsTemplate jmsTemplate;

  @Override
  public void handle(ThingDtoSave thingDtoSave) {
    jmsTemplate.convertAndSend(JmsConfig.THINGSAVE_Q, thingDtoSave);
  }
}
