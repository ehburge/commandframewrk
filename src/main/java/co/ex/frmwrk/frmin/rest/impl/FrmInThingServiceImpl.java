package co.ex.frmwrk.frmin.rest.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.frmin.rest.FrmInThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrmInThingServiceImpl implements FrmInThingService {

  @Autowired private CommandBusDrivingApp commandBusDrivingApp;

  @Override
  public void perform(AppCommand appCommand) {
    commandBusDrivingApp.perform(appCommand);
  }
}
