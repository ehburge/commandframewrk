package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Component
public class PersistListener {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  private CommandHandlerDrivenFrm commandHandlerDrivenFrm;

  private int nbrMsgs = 0;

  public PersistListener(@Qualifier("jpa") CommandHandlerDrivenFrm commandHandlerDrivenFrm) {
    this.commandHandlerDrivenFrm = commandHandlerDrivenFrm;
  }

  void addToNbrMsgs() {
    int oldValue = nbrMsgs;
    nbrMsgs++;
    int newValue = nbrMsgs;
    support.firePropertyChange("value", oldValue, newValue);
  }
  public int getNbrMsgs() {
    return nbrMsgs;
  }

  @JmsListener(destination = JmsConfig.PERSIST_Q_NAME)
  public void listen(
      @Payload ThingDto thingDto, @Headers MessageHeaders messageHeaders, Message message) {

    addToNbrMsgs();

    System.out.println("Got a message " + nbrMsgs);

    System.out.println(thingDto);

    commandHandlerDrivenFrm.handle(thingDto);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
