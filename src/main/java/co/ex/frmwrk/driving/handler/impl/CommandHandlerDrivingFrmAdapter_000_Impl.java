package co.ex.frmwrk.driving.handler.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppThingCommand000;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;
import co.ex.frmwrk.frmin.cmd.impl.FrmInThingCommand000;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import java.util.Map;
import co.ex.frmwrk.mapping.FrmInCommandToAppCommandMapper000;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Builder
@Component
public class CommandHandlerDrivingFrmAdapter_000_Impl implements CommandHandlerDrivingFrmAdapter {

  @Builder.Default
  private FrmInCommandToAppCommandMapper000 frmInCommandToAppCommandMapper000 = Mappers.getMapper(FrmInCommandToAppCommandMapper000.class);

  private final Map<Class<? extends AppCommand>, CommandBusDrivingApp> commandBusDrivingAppMap;


  @Override
  public void handle(FrmInThingCommand frmInThingCommand) {
    AppThingCommand000 appThingCommand_000 =
            frmInCommandToAppCommandMapper000.frmInThingCommandToAppCommand(
            (FrmInThingCommand000) frmInThingCommand);
    commandBusDrivingAppMap.get(appThingCommand_000.getClass()).perform(appThingCommand_000);
  }
}
