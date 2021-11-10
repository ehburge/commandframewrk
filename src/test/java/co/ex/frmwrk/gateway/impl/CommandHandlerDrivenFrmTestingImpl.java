package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.ThingDtoMapDelegate;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CommandHandlerDrivenFrmTestingImpl implements CommandHandlerDrivenFrm {

  private final ThingDtoMapDelegate thingDtoMapDelegate;

  @Inject
  public CommandHandlerDrivenFrmTestingImpl(ThingDtoMapDelegateImpl thingDtoMapDelegate) {
    this.thingDtoMapDelegate = thingDtoMapDelegate;
  }

  @Override
  public void handle(ThingDto thingDto) {
    thingDtoMapDelegate.put(thingDto.getThingNbr(), thingDto);
  }

  public ThingDtoMapDelegate getThingDtoMapDelegate() {
    return thingDtoMapDelegate;
  }
}
