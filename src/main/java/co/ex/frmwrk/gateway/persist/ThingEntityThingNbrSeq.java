package co.ex.frmwrk.gateway.persist;

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

  public void setThingNbrWhenNull(ThingEntity thingEntity) {
    if (thingEntity.getThingNbr() == null) {
      thingEntity.setThingNbr(incrementer.nextLongValue());
    }
  }
}
