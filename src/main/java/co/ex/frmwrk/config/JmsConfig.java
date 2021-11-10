package co.ex.frmwrk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

public class JmsConfig {

  public static final String PERSIST_Q_NAME = "persist.queue";

  @Bean
  public MessageConverter messageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(java.awt.TrayIcon.MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
}
