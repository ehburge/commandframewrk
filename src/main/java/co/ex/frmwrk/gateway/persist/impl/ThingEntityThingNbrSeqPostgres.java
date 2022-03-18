package co.ex.frmwrk.gateway.persist.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntityThingNbrSeq;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component()
public class ThingEntityThingNbrSeqPostgres implements ThingEntityThingNbrSeq {

  DataFieldMaxValueIncrementer incrementer;

  public ThingEntityThingNbrSeqPostgres( DataSource dataSource) {
    incrementer = new PostgresSequenceMaxValueIncrementer(dataSource, "thing_sequence");
  }

  public void setThingNbrWhenNull(ThingDtoSave thingDtoSave) {
    if (thingDtoSave.getThingNbr() == null) {
      thingDtoSave.setThingNbr(incrementer.nextLongValue());
    }
  }
}
