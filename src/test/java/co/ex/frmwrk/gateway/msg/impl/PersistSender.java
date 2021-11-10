package co.ex.frmwrk.gateway.msg.impl;

import co.ex.app.driven.gateway.AppThingCommandSave;
import co.ex.frmwrk.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class PersistSender {

  private final JmsTemplate jmsTemplate;

  // @Scheduled(fixedDelay = 2000)
  public void sendMessage() {

    AppThingCommandSave appThingCommandSave =
        AppThingCommandSave.builder()
            .thingNbr(new Random().nextLong())
            .description("desc")
            .fullDescription("fullDesc")
            .price(new BigDecimal("123.45"))
            .build();

    System.out.println("Sending message");
    jmsTemplate.convertAndSend(JmsConfig.PERSIST_Q_NAME, appThingCommandSave);

    System.out.println(appThingCommandSave);
  }
}
