package co.ex.frmwrk.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FrmWrkConfig {

  // Driving
  //
  //  @Bean
  //  public CommandBusDrivingApp makeCommandBusDriving() {
  //    CommandBusDrivingApp commandBusDrivingApp =
  //    return commandBusDrivingApp;
  //  }
  //
  //  // Driven
  //
  //  @Bean
  //  public Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap(
  //      CommandBusDrivenFrm commandBusDrivenFrm) {
  //    Map<Class, CommandBusDrivenFrm> commandBusDrivenFrmMap = new HashMap<>();
  //    commandBusDrivenFrmMap.put(AppThingCommandSave.class, commandBusDrivenFrm);
  //
  //    return commandBusDrivenFrmMap;
  //  }
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
}
