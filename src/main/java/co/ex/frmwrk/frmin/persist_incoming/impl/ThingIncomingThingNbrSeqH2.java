package co.ex.frmwrk.frmin.persist_incoming.impl;

import co.ex.frmwrk.frmin.persist_incoming.ThingIncomingThingNbrSeq;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.H2SequenceMaxValueIncrementer;import org.springframework.stereotype.Component;import javax.sql.DataSource;

@Profile("h2-mem")
@Component
public class ThingIncomingThingNbrSeqH2 implements ThingIncomingThingNbrSeq {

  private H2SequenceMaxValueIncrementer h2SequenceMaxValueIncrementer;
  public ThingIncomingThingNbrSeqH2(DataSource dataSource) {
    h2SequenceMaxValueIncrementer = new H2SequenceMaxValueIncrementer(dataSource, "thing_incoming_seq");
  }

  public Long setThingNbrWhenNull(Long thingNbr) {
    if (thingNbr == null) {
      return h2SequenceMaxValueIncrementer.nextLongValue();
    }
    return thingNbr;
  }
}
