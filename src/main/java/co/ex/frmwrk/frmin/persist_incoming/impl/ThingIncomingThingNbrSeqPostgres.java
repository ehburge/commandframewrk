package co.ex.frmwrk.frmin.persist_incoming.impl;

import co.ex.frmwrk.frmin.persist_incoming.ThingIncomingThingNbrSeq;
import javax.sql.DataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;

@Profile("!h2-mem")
@Component
public class ThingIncomingThingNbrSeqPostgres implements ThingIncomingThingNbrSeq {

  DataFieldMaxValueIncrementer incrementer;

  public ThingIncomingThingNbrSeqPostgres(DataSource dataSource) {
    incrementer = new PostgresSequenceMaxValueIncrementer(dataSource, "thingincoming_sequence");
  }

  public Long setThingNbrWhenNull(Long thingNbr) {
    if (thingNbr == null) {
      return incrementer.nextLongValue();
    }
    return thingNbr;
  }
}
