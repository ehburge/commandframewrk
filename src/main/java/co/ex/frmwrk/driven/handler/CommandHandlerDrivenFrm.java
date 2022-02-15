package co.ex.frmwrk.driven.handler;

import co.ex.frmwrk.cmd.FrmCommandHandler;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;

public interface CommandHandlerDrivenFrm<Dto extends ThingDtoSave> extends FrmCommandHandler {

  void handle(Dto thingDto);
}
