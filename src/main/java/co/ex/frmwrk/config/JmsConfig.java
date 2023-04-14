package co.ex.frmwrk.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JmsConfig {

  //  public static final String THINGSAVE_Q = "thingsave.queue";
  public static final String EVENT_Q = "event.queue";
  public static final String SEND_LISTEN_TOPIC = "send.listen.topic";

  @Bean(name = "jmsTemplate")
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(singleConnectionFactory());
    template.setMessageConverter(messageConverter());
    return template;
  }

  @Bean(name = "jmsTemplateAnycast")
  public JmsTemplate jmsTemplateAnycast() {
    JmsTemplate template = new JmsTemplate();
    template.setPubSubDomain(false);
    template.setConnectionFactory(singleConnectionFactory());
    template.setMessageConverter(messageConverter());
    return template;
  }

  @Bean(name = "jmsTemplateMulticast")
  public JmsTemplate jmsTemplateMulticast() {
    JmsTemplate template = new JmsTemplate();
    template.setPubSubDomain(true);
    template.setConnectionFactory(singleConnectionFactory());
    template.setMessageConverter(messageConverter());
    return template;
  }

  //  @Bean
  //  public JmsPoolConnectionFactory pooledConnectionFactoryOnline() {
  //    JmsPoolConnectionFactory poolingFactory = new JmsPoolConnectionFactory();
  //    poolingFactory.setConnectionFactory(senderActiveMQConnectionFactory());
  //    poolingFactory.setMaxConnections(3);
  //    poolingFactory.setConnectionIdleTimeout(0);
  //
  //    return poolingFactory;
  //  }
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(singleConnectionFactory());
    factory.setConcurrency("1-1");
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(false);
    factory.setMessageConverter(messageConverter());
    return factory;
  }
  // https://github.com/zorro2b/artemis-springboot/blob/main/src/main/java/com/example/amqdemo/AmqdemoApplication.java
  //  @Bean
  //  public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
  // DefaultJmsListenerContainerFactoryConfigurer configurer) {
  //    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
  //
  //    //logger.info("Configuring JMS with clientId "+clientId);
  //    CachingConnectionFactory ccFactory = cachingConnectionFactory();
  //    // This provides all boot's default to this factory, including the message converter
  //    configurer.configure(factory, ccFactory);
  //    factory.setPubSubDomain(true);
  //    factory.setClientId("topic-clientid");
  //    factory.setSubscriptionDurable(true);
  ////		factory.setSubscriptionShared(true);
  ////		factory.setConcurrency("1");
  //    ccFactory.setClientId("topic-clientid");
  //
  //    return factory;
  //  }

  //  //
  // https://www.tabnine.com/code/java/classes/org.springframework.jms.config.DefaultJmsListenerContainerFactory
  //  @Bean
  //  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
  //      SingleConnectionFactory singleConnectionFactory,
  //      DefaultJmsListenerContainerFactoryConfigurer configurer) {
  //    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
  //    configurer.configure(factory, singleConnectionFactory);
  //    singleConnectionFactory.setClientId("brokerClientId");
  //    factory.setPubSubDomain(true);
  //    factory.setSubscriptionDurable(true);
  //    factory.setClientId("brokerClientId");
  //    return factory;
  //  }

  //  @Bean
  //  public JmsListenerContainerFactory<?> myFactory(
  //          ConnectionFactory connectionFactory,
  //          DefaultJmsListenerContainerFactoryConfigurer configurer) {
  //    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
  //    configurer.configure(factory, connectionFactory);
  //    return factory;
  //  }
  @Value("${spring.artemis.broker-url:}")
  private String brokerUrl;
  // https://codenotfound.com/spring-jms-topic-example.html
  @Bean
  public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
    return new ActiveMQConnectionFactory();
  }

  @Bean
  public SingleConnectionFactory singleConnectionFactory() {
    SingleConnectionFactory connectionFactory =
        new SingleConnectionFactory(senderActiveMQConnectionFactory());
    connectionFactory.setClientId("thing");
    return connectionFactory;
  }

  //  @Bean
  //  public CachingConnectionFactory cachingConnectionFactory() {
  //    return new CachingConnectionFactory( senderActiveMQConnectionFactory() );
  //  }
  //
  //  @Bean
  //  public JmsTopicTemplate jmsTopicTemplate() {
  //
  //    JmsTopicTemplate jmsTemplate =
  //            new JmsTopicTemplate(cachingConnectionFactory());
  //    jmsTemplate.setPubSubDomain(true);
  //
  //    return jmsTemplate;
  //  }
  ////  @Value("${activemq.broker-url}")
  ////  private String brokerUrl;
  //
  //    @Bean
  //    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
  //      ActiveMQConnectionFactory activeMQConnectionFactory =
  //              new ActiveMQConnectionFactory();
  //      //activeMQConnectionFactory.setBrokerURL(brokerUrl);
  //
  //      return activeMQConnectionFactory;
  //    }
  //
  //  @Bean
  //  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
  //    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
  //    factory.setConnectionFactory(receiverActiveMQConnectionFactory());
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
