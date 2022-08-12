package co.ex.frmwrk.driven.handler;

import co.ex.frmwrk.cmd.FrmCommandHandler;
import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;

public interface CommandHandlerDrivenFrm<Dto extends ThingDto> extends FrmCommandHandler {

  void handle(Dto thingDto);
}
