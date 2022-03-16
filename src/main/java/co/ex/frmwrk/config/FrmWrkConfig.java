package co.ex.frmwrk.config;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.frmin.rest.FrmInThingCommand;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FrmWrkConfig {

  final CommandHandlerDrivenFrm commandHandlerDrivenFrm;

  public FrmWrkConfig(CommandHandlerDrivenFrm commandHandlerDrivenFrm) {
    this.commandHandlerDrivenFrm = commandHandlerDrivenFrm;
  }

  @Bean
  public Map<Class<?>, CommandBusDrivenFrm> commandBusDrivenFrmMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {

    Map<Class<?>, CommandBusDrivenFrm> cbDrivenFrm_map = new HashMap<>();
    cbDrivenFrm_map.put(AppThingCommandSave.class, commandBusDrivenFrm);

    // setupMapBeans.putCommandBusDrivenFrm(cbDrivenFrm_map);
    return cbDrivenFrm_map;
  }

  @Bean
  public Map<Class<?>, CommandHandlerDrivenFrm> makeCommandHandlerDrivenFrmMap() {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrm_map = new HashMap<>();
    chDrivenFrm_map.put( ThingDtoSave.class, commandHandlerDrivenFrm);

    return chDrivenFrm_map;
  }
}
  //
  //  @Bean
  //  public CommandHandlerDrivenApp attachSaveThingAccessAdapter(
  //      Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap) {
  //    CommandHandlerDrivenApp commandHandlerDrivenAppImpl =
  //        injector.getInstance(CommandHandlerDrivenApp.class);
  //
  //    commandHandlerDrivenAppImpl.setCommandBusDrivenFrmMap(commandBusDrivenFrmMap);
  //
  //    return commandHandlerDrivenAppImpl;
  //  }
  //
  //  @Bean
  //  public Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap(
  //      CommandHandlerDrivenFrmSaveMsgImpl commandHandlerDrivenFrmSaveMsgImpl) {
  //    Map<Class, CommandHandlerDrivenFrm> commandHandlerDrivenFrmMap = new HashMap<>();
  //    commandHandlerDrivenFrmMap.put(ThingDtoSave.class, commandHandlerDrivenFrmSaveMsgImpl);
  //
  //    return commandHandlerDrivenFrmMap;
  //  }
  //
  //  @Bean
  //  public Map<Class, PersistThingDto> makeThingAccessAdapterPersistMap(
  //      PersistThingDto outsideAdapterSaveJPA, PersistThingDto saveThingAccessAdapterJPA) {
  //
  //    Map<Class, PersistThingDto> thingAccessAdapterPersistMap = new HashMap<>();
  //    thingAccessAdapterPersistMap.put(AppThingCommandSave.class, outsideAdapterSaveJPA);
  //    thingAccessAdapterPersistMap.put(ThingDtoSave.class, saveThingAccessAdapterJPA);
  //
  //    return thingAccessAdapterPersistMap;
  //  }
