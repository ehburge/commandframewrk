package co.ex.frmwrk;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

//@SpringBootApplication
//@EnableJms
public class ProtoApp {

  private final String BROKER_URL = "tcp://localhost:61616";
  private final String BROKER_USERNAME = "artemis";
  private final String BROKER_PASSWORD = "artemis";

  public static void main(String[] args) throws Exception {
    final ConfigurableApplicationContext context = SpringApplication.run(ProtoApp.class);
    System.out.println("********************* Sending message...");

    JmsTemplate jmsTemplate = context.getBean("jmsTemplate", JmsTemplate.class);
    JmsTemplate jmsTemplateAnycast = context.getBean("jmsTemplateAnycast", JmsTemplate.class);
    JmsTemplate jmsTemplateMulticast = context.getBean("jmsTemplateMulticast", JmsTemplate.class);

    jmsTemplateAnycast.convertAndSend("VirtualTopic.send-listen", "Hello world anycast!");
    jmsTemplate.convertAndSend(
        "anycast://VirtualTopic.send-listen", "Hello world anycast using prefix!");
    jmsTemplateMulticast.convertAndSend("VirtualTopic.send-listen", "Hello world multicast!");
    jmsTemplate.convertAndSend(
        "multicast://VirtualTopic.send-listen", "Hello world multicast using prefix!");

    System.out.print("Press any key to close the context");
    System.in.read();

    context.close();
  }

  @Bean
  public ActiveMQConnectionFactory connectionFactory() throws JMSException {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(BROKER_URL);
    connectionFactory.setUser(BROKER_USERNAME);
    connectionFactory.setPassword(BROKER_PASSWORD);
    return connectionFactory;
  }

//  @Bean
//  public JmsTemplate jmsTemplate() throws JMSException {
//    JmsTemplate template = new JmsTemplate();
//    template.setConnectionFactory(connectionFactory());
//    return template;
//  }
//
//  @Bean
//  public JmsTemplate jmsTemplateAnycast() throws JMSException {
//    JmsTemplate template = new JmsTemplate();
//    template.setPubSubDomain(false);
//    template.setConnectionFactory(connectionFactory());
//    return template;
//  }
//
//  @Bean
//  public JmsTemplate jmsTemplateMulticast() throws JMSException {
//    JmsTemplate template = new JmsTemplate();
//    template.setPubSubDomain(true);
//    template.setConnectionFactory(connectionFactory());
//    return template;
//  }
//
//  @Bean
//  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
//    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//    factory.setConnectionFactory(connectionFactory());
//    factory.setConcurrency("1-1");
//    factory.setPubSubDomain(true);
//    return factory;
//  }

  @JmsListener(destination = "Consumer.A.VirtualTopic.send-listen")
  public void receiveMessageFromA(String message) {
    System.out.println("*********************** MESSAGE RECEIVED FROM A: " + message);
  }

  @JmsListener(destination = "Consumer.B.VirtualTopic.send-listen")
  public void receiveMessageFromB(String message) {
    System.out.println("*********************** MESSAGE RECEIVED FROM B: " + message);
  }

  @JmsListener(destination = "VirtualTopic.send-listen")
  public void receiveMessageFromTopic(String message) {
    System.out.println("*********************** MESSAGE RECEIVED FROM TOPIC: " + message);
  }
}
