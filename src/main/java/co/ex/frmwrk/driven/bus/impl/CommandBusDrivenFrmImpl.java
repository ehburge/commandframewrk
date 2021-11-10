package co.ex.frmwrk.driven.bus.impl;

import cmd.AppCommand;
import cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.handler.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.mapping.AppThingCommandSaveThingDtoSaveMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class CommandBusDrivenFrmImpl implements CommandBusDrivenFrm {

    private Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap;

    @Override
    public void perform(AppCommand appCommand) {
        ThingDtoSave thingDtoSave = AppThingCommandSaveThingDtoSaveMapper.INSTANCE.appThingCommandSaveToThingDtoSave((AppThingCommandSave) appCommand);
        CommandHandlerDrivenFrm drivenApp = commandHandlerDrivenFrmMap.get(thingDtoSave.getClass());
        drivenApp.handle(thingDtoSave);
    }
}
