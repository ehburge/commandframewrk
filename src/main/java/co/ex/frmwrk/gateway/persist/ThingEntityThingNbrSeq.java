package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ThingEntityThingNbrSeq {

  DataFieldMaxValueIncrementer incrementer;

  public ThingEntityThingNbrSeq(DataSource dataSource) {
    incrementer = new PostgresSequenceMaxValueIncrementer(dataSource, "thing_sequence");
  }

  public void setThingNbrWhenNull(ThingDtoSave thingDtoSave) {
    if (thingDtoSave.getThingNbr() == null) {
      thingDtoSave.setThingNbr(incrementer.nextLongValue());
    }
  }
}
