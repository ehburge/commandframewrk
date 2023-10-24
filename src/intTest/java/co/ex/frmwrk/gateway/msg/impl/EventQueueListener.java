package co.ex.frmwrk.gateway.msg.impl;

import co.ex.eventer.JsonMapper;
import co.ex.eventer.command.Constants;
import co.ex.framewrk.eventer.model.impl000.CommandDtoSaveEvent000;
import co.ex.frmwrk.config.JmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventQueueListener {
  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @JmsListener(destination = "anycast://" + JmsConfig.EVENT_Q)
  //  public void listen(CommandDtoSaveEvent000 commandDtoEvent) {
  public void listen(
      @Payload CommandDtoSaveEvent000 commandDtoEvent,
      @Headers MessageHeaders messageHeaders,
      Message message) {
    String clsName = (String) messageHeaders.get(Constants.CLASS_NAME);
    LOGGER.info(
        Constants.CLASS_NAME
            .concat("=")
            .concat(clsName)
            .concat(System.lineSeparator())
            .concat("***--- ".concat(clsName).concat(" received ").concat(System.lineSeparator())
                    + JsonMapper.toJson(commandDtoEvent)));
  }
}
