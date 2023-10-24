package co.ex.frmwrk.frmin.persist_incoming.impl;

import co.ex.frmwrk.frmin.persist_incoming.CommandIncomingIdSeq;
import javax.sql.DataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;

@Profile("!h2-mem")
@Component
public class IdSeqPostgres implements CommandIncomingIdSeq {

  DataFieldMaxValueIncrementer incrementer;

  public IdSeqPostgres(DataSource dataSource) {
    incrementer = new PostgresSequenceMaxValueIncrementer(dataSource, "commandincoming_sequence");
  }

  public Long setCommandNbrWhenNull(Long commandNbr) {
    if (commandNbr == null) {
      return incrementer.nextLongValue();
    }
    return commandNbr;
  }
}
