package co.ex.frmwrk.gateway.msg.impl;

import co.ex.frmwrk.config.JmsConfig;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.impl.PersistCommandDto;
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

  private final PersistCommandDto persistCommandDto;
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private AtomicInteger nbrMsgs = new AtomicInteger();

  @Autowired
  public CommandHandlerDrivenFrmSaveMsgListener(
      @Qualifier("jmsTemplateMulticast") JmsTemplate jmsTemplate, PersistCommandDto persistCommandDto) {
    this.jmsTemplate = jmsTemplate;
    this.persistCommandDto = persistCommandDto;
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
      @Payload DtoCommandSave dtoCommandSave,
      @Headers MessageHeaders messageHeaders,
      Message message) {
    // CommandDtoSave commandDtoSave = JsonMapper.fromJson(str_commandDtoSave, CommandDtoSave.class);

    addToNbrMsgs();

    LOGGER.debug("Got a message " + nbrMsgs);

    //jmsTemplate.convertAndSend("multicast://".concat(JmsConfig.SEND_LISTEN_TOPIC), commandDtoSave000);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
