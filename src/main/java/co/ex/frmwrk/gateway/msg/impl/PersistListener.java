package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.config.JmsConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PersistListener {

  private CommandHandlerDrivenFrm commandHandlerDrivenFrm;

  private int nbrMsgs = 0;

  public PersistListener(@Qualifier("jpa") CommandHandlerDrivenFrm commandHandlerDrivenFrm) {
    this.commandHandlerDrivenFrm = commandHandlerDrivenFrm;
  }

  public int getNbrMsgs() {
    return nbrMsgs;
  }

  @JmsListener(destination = JmsConfig.PERSIST_Q_NAME)
  public void listen(
      @Payload ThingDto thingDto, @Headers MessageHeaders messageHeaders, Message message) {

    nbrMsgs++;
    System.out.println("Got a message " + nbrMsgs);

    System.out.println(thingDto);

//    try {
//      Thread.sleep(500);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    commandHandlerDrivenFrm.handle(thingDto);
  }
}
