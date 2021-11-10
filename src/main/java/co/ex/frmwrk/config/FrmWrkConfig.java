package co.ex.frmwrk.config;

import cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.handler.impl.CommandHandlerDrivenAppImpl;
import co.ex.app.driven.gateway.AppThingCommandSave;
import co.ex.app.driven.gateway.ThingDtoSave;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.app.driven.gateway.ThingAccessAdapterPersist;
import co.ex.app.driven.gateway.impl.CommandHandlerDrivenDomainCreateThingImpl;
import co.ex.app.driven.gateway.impl.CommandHandlerDrivenDomainSaveThingImpl;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.domain.cmd.impl.CreateThingCommand;
import co.ex.domain.cmd.impl.SaveThingCommand;
import co.ex.domain.driven.cmd.handler.CommandHandlerDrivenDomain;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.jpa.impl.CommandHandlerDrivenFrmSaveJPA;
import co.ex.frmwrk.gateway.msg.impl.CommandHandlerDrivenFrmSaveMsg;
import co.ex.frmwrk.guice.injector.FrmWrkApp;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FrmWrkConfig {

  @Bean
  public CommandHandlerDrivenFrm attachSaveThingAccessAdapter(
      //@Qualifier("saveCommandHandlerDrivenFrmMsg")
              CommandHandlerDrivenFrm saveCommandHandlerDrivenFrm) {
    Injector injector = makeGuiceInjector();
    CommandHandlerDrivenAppImpl commandHandlerDrivenAppImpl =
        injector.getInstance(CommandHandlerDrivenAppImpl.class);

    commandHandlerDrivenAppImpl.

    saveThingCommandAdapterDomainImpl.setThingAccessAdapter(saveCommandHandlerDrivenFrm);

    return saveThingCommandAdapterDomainImpl;
  }

  @Bean
  public Map<Class, CommandHandlerDrivenFrm> makeThingAccessAdapterMsgMap(
          CommandHandlerDrivenFrmImpl createThingAccessAdapterMsg,
      CommandHandlerDrivenFrmSaveMsg saveThingAccessAdapterMsg) {
    Map<Class, CommandHandlerDrivenFrm> thingAccessAdapterMap = new HashMap<>();
    thingAccessAdapterMap.put(ThingDtoSave.class, createThingAccessAdapterMsg);
    thingAccessAdapterMap.put(ThingDtoSave.class, saveThingAccessAdapterMsg);
    return thingAccessAdapterMap;
  }

  @Bean
  public Map<Class, CommandHandlerDrivenFrmSaveJPA> makeThingAccessAdapterPersistMap(
          CommandHandlerDrivenFrmSaveJPA outsideAdapterSaveJPA,
      CommandHandlerDrivenFrmSaveJPA saveThingAccessAdapterJPA) {
    Map<Class, CommandHandlerDrivenFrmSaveJPA> thingAccessAdapterPersistMap = new HashMap<>();
    thingAccessAdapterPersistMap.put(AppThingCommandSave.class, outsideAdapterSaveJPA);
    thingAccessAdapterPersistMap.put(ThingDtoSave.class, saveThingAccessAdapterJPA);
    return thingAccessAdapterPersistMap;
  }

  @Bean
  public CommandBusDrivingApp makeCommandBusDriving() {
    Injector injector = makeGuiceInjector();
    CommandBusDrivingApp commandBusDrivingApp = injector.getInstance(CommandBusDrivingApp.class);

    return commandBusDrivingApp;
  }

  @Bean
  public Injector makeGuiceInjector() {
    return FrmWrkApp.getInjector();
  }
}
