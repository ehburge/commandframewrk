package co.ex.frmwrk.gateway.persist.impl;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntityThingNbrSeq;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("dev")
@Component
public class ThingEntityThingNbrSeqDev implements ThingEntityThingNbrSeq {
  private static final Random rndNbr = new Random();

  public void setThingNbrWhenNull(ThingDtoSave thingDtoSave) {
    if (thingDtoSave.getThingNbr() == null) {
      thingDtoSave.setThingNbr(rndNbr.nextLong());
    }
  }
}
