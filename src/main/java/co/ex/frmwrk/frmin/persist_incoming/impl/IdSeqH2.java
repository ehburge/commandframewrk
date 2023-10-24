package co.ex.frmwrk.frmin.persist_incoming.impl;

import co.ex.frmwrk.frmin.persist_incoming.CommandIncomingIdSeq;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.H2SequenceMaxValueIncrementer;import org.springframework.stereotype.Component;import javax.sql.DataSource;

@Profile("h2-mem")
@Component
public class IdSeqH2 implements CommandIncomingIdSeq {

  private H2SequenceMaxValueIncrementer h2SequenceMaxValueIncrementer;
  public IdSeqH2(DataSource dataSource) {
    h2SequenceMaxValueIncrementer = new H2SequenceMaxValueIncrementer(dataSource, "command_incoming_seq");
  }

  public Long setCommandNbrWhenNull(Long commandNbr) {
    if (commandNbr == null) {
      return h2SequenceMaxValueIncrementer.nextLongValue();
    }
    return commandNbr;
  }
}
