package co.ex.frmwrk.driving.handler.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInCommand;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;

import java.util.Map;
import co.ex.frmwrk.mapping.FrmInCommandToAppCommandMapper;
import lombok.Builder;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Builder
@Component
public class CommandHandlerDrivingFrmAdapter_000_Impl implements CommandHandlerDrivingFrmAdapter {

  @Builder.Default
  private FrmInCommandToAppCommandMapper frmInCommandToAppCommandMapper = Mappers.getMapper(FrmInCommandToAppCommandMapper.class);

  private final Map<Class<? extends AppCommand>, CommandBusDrivingApp> commandBusDrivingAppMap;


  @Override
  public void handle(FrmInCommand frmInCommand) {
    AppCommandSave appCommandSave =
            frmInCommandToAppCommandMapper.frmInCommandToAppCommand(
            (FrmInCommandSave) frmInCommand);
    commandBusDrivingAppMap.get(appCommandSave.getClass()).perform(appCommandSave);
  }
}
