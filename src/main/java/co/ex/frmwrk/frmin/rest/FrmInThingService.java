package co.ex.frmwrk.frmin.rest;

import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;

public interface FrmInThingService {

  void perform(FrmInThingCommand appCommand);
}
