package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.gateway.persist.impl.PersistThingDto;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CommandHandlerDrivenFrmSaveMsgListener {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  private final JmsTemplate jmsTemplate;

  private final PersistThingDto persistThingDto;
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private AtomicInteger nbrMsgs = new AtomicInteger();

  @Autowired
  public CommandHandlerDrivenFrmSaveMsgListener(
      @Qualifier("jmsTemplateMulticast") JmsTemplate jmsTemplate, PersistThingDto persistThingDto) {
    this.jmsTemplate = jmsTemplate;
    this.persistThingDto = persistThingDto;
  }

  void addToNbrMsgs() {
    int oldValue = nbrMsgs.get();

    int newValue = nbrMsgs.addAndGet(1);
    support.firePropertyChange("value", oldValue, newValue);
  }

  public AtomicInteger getNbrMsgs() {
    return nbrMsgs;
  }

  @JmsListener(destination = JmsConfig.SEND_LISTEN_TOPIC)
  public void listen(
      @Payload ThingDtoSave000 thingDtoSave000,
      @Headers MessageHeaders messageHeaders,
      Message message) {
    // ThingDtoSave thingDtoSave = JsonMapper.fromJson(str_thingDtoSave, ThingDtoSave.class);

    addToNbrMsgs();

    LOGGER.debug("Got a message " + nbrMsgs);

    //jmsTemplate.convertAndSend("multicast://".concat(JmsConfig.SEND_LISTEN_TOPIC), thingDtoSave000);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
