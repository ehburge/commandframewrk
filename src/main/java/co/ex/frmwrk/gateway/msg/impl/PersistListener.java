package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.impl.PersistThingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@RequiredArgsConstructor
@Component
public class PersistListener {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  private final JmsTemplate jmsTemplate;
  private final PersistThingDto persistThingDto;

  private int nbrMsgs = 0;

  void addToNbrMsgs() {
    int oldValue = nbrMsgs;
    nbrMsgs++;
    int newValue = nbrMsgs;
    support.firePropertyChange("value", oldValue, newValue);
  }

  public int getNbrMsgs() {
    return nbrMsgs;
  }

  @JmsListener(destination = JmsConfig.THINGSAVE_Q)
  public void listen(
      @Payload ThingDtoSave thingDtoSave, @Headers MessageHeaders messageHeaders, Message message) {

    addToNbrMsgs();

    System.out.println("Got a message " + nbrMsgs);

    System.out.println(thingDtoSave);

    ThingDtoSave thingDtoSaveRtn = persistThingDto.persist(thingDtoSave);

    jmsTemplate.convertAndSend(JmsConfig.EVENT_Q, thingDtoSaveRtn);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
