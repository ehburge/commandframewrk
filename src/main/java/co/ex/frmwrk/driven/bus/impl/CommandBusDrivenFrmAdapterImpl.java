package co.ex.frmwrk.driven.bus.impl;

import cmd.AppCommand;
import cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.handler.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class CommandBusDrivenFrmAdapterImpl implements CommandBusDrivenFrm {

  private Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap;

  @Autowired
  public CommandBusDrivenFrmAdapterImpl(Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap) {
    this.commandHandlerDrivenFrmMap = commandHandlerDrivenFrmMap;
  }

  @Override
  public void perform(AppCommand appCommand) {
    ThingDtoSave thingDtoSave =
        AppThingCommandSaveThingDtoSaveMapper.INSTANCE.appThingCommandSaveToThingDtoSave(
            (AppThingCommandSave) appCommand);
    CommandHandlerDrivenFrm drivenApp = commandHandlerDrivenFrmMap.get(thingDtoSave.getClass());
    drivenApp.handle(thingDtoSave);
  }
}
