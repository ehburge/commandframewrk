package co.ex.frmwrk.driving.bus.impl;

import co.ex.frmwrk.driving.bus.CommandBusDrivingFrmAdapter;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInCommand;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBusDrivingFrmAdapterImpl implements CommandBusDrivingFrmAdapter {

  private final Map<Class<?>, CommandHandlerDrivingFrmAdapter> commandHandlerDrivingFrmMap;

  @Autowired
  public CommandBusDrivingFrmAdapterImpl(
      Map<Class<?>, CommandHandlerDrivingFrmAdapter> commandHandlerDrivingFrmMap) {
    this.commandHandlerDrivingFrmMap = commandHandlerDrivingFrmMap;
  }

  @Override
  public void perform(FrmInCommand frmInCommand) {
    commandHandlerDrivingFrmMap.get(frmInCommand.getClass()).handle(frmInCommand);
  }
}
