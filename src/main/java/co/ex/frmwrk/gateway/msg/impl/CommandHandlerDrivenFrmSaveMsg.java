package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommandHandlerDrivenFrmSaveMsg implements CommandHandlerDrivenFrm<ThingDtoSave> {

  private final JmsTemplate jmsTemplate;

  @Override
  public void handle(ThingDtoSave thingDtoSave) {
    jmsTemplate.convertAndSend(JmsConfig.THINGSAVE_Q, thingDtoSave);
  }
}
