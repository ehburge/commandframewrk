package co.ex.frmwrk.config;

import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@Configuration
public class JmsConfig_IntTest {

  @Bean(initMethod = "start", destroyMethod = "stop")
  public EmbeddedActiveMQ embeddedActiveMQ() throws Exception {
    final EmbeddedActiveMQ embeddedActiveMQ = new EmbeddedActiveMQ();

    return embeddedActiveMQ;
    }
}
