package co.ex.frmwrk.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JmsConfig {

  public static final String THINGSAVE_Q = "thingsave.queue";
  public static final String EVENT_Q = "event.queue";

//  @Value("${activemq.broker-url}")
//  private String brokerUrl;
  //https://codenotfound.com/spring-jms-topic-example.html
//  @Bean
//  public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
//    ActiveMQConnectionFactory activeMQConnectionFactory =
//            new ActiveMQConnectionFactory();
//    //activeMQConnectionFactory.setBrokerURL(brokerUrl);
//
//    return activeMQConnectionFactory;
//  }
//
//  @Bean
//  public CachingConnectionFactory cachingConnectionFactory() {
//    return new CachingConnectionFactory(
//            senderActiveMQConnectionFactory());
//  }
//
//  @Bean
//  public JmsTemplate jmsTemplate() {
//    JmsTemplate jmsTemplate =
//            new JmsTemplate(cachingConnectionFactory());
//    jmsTemplate.setPubSubDomain(true);
//
//    return jmsTemplate;
//  }
////  @Value("${activemq.broker-url}")
////  private String brokerUrl;
//
//  @Bean
//  public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
//    ActiveMQConnectionFactory activeMQConnectionFactory =
//            new ActiveMQConnectionFactory();
//    //activeMQConnectionFactory.setBrokerURL(brokerUrl);
//
//    return activeMQConnectionFactory;
//  }
//
//  @Bean
//  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//    DefaultJmsListenerContainerFactory factory =
//            new DefaultJmsListenerContainerFactory();
//    factory
//            .setConnectionFactory(receiverActiveMQConnectionFactory());
//    factory.setPubSubDomain(true);
//
//    return factory;
//  }

  @Bean
  public MessageConverter messageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
}
