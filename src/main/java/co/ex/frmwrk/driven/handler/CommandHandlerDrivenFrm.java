package co.ex.frmwrk.driven.handler;

import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;

public interface CommandHandlerDrivenFrm {

  void handle(ThingDto thingDto);
}
