package co.ex.frmwrk.config;

import co.ex.app.cmd.AppCommand;
import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driven.cmd.bus.CommandBusDrivenFrm;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.frmwrk.driven.handler.CommandHandlerDrivenFrm;
import co.ex.frmwrk.driving.bus.CommandBusDrivingFrmAdapter;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInCommand;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import co.ex.frmwrk.gateway.Dto;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.send.bus.DtoSenderBus;
import co.ex.frmwrk.send.bus.impl.DtoSenderBusImpl;
import co.ex.frmwrk.send.handler.DtoSenderHandler;
import co.ex.frmwrk.send.handler.impl.DtoSenderHandler000Impl;
import co.ex.frmwrk.send.handler.impl.DtoSenderHandler100Impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrmWrkConfig {

  private final BeanFactory beanFactory;

  private final AppSetupMapBeans appSetupMapBeans;

  public FrmWrkConfig(BeanFactory beanFactory, AppSetupMapBeans appSetupMapBeans) {
    this.beanFactory = beanFactory;
    this.appSetupMapBeans = appSetupMapBeans;
  }

  @Bean
  public Map<Class<?>, CommandBusDrivingFrmAdapter> makeCommandBusDrivingFrmAdapterMap(
      CommandBusDrivingFrmAdapter commandBusDrivingFrmAdapter) {

    Map<Class<?>, CommandBusDrivingFrmAdapter> commandBusDrivingFrmAdapterMap = new HashMap<>();
    commandBusDrivingFrmAdapterMap.put(FrmInCommandSave.class, commandBusDrivingFrmAdapter);
    return commandBusDrivingFrmAdapterMap;
  }

  @Bean
  public Map<Class<? extends FrmInCommand>, CommandHandlerDrivingFrmAdapter>
      makeCommandHandlerDrivingFrmMap(CommandHandlerDrivingFrmAdapter handlerDrivingFrmAdapter) {

    Map<Class<? extends FrmInCommand>, CommandHandlerDrivingFrmAdapter> handlerDrivingFrmAdapterMap = new HashMap<>();
    handlerDrivingFrmAdapterMap.put(FrmInCommandSave.class, handlerDrivingFrmAdapter);
    return handlerDrivingFrmAdapterMap;
  }

  @Bean
  public Map<Class<? extends AppCommand>, CommandBusDrivingApp> getAppCommandBusMap() {
    return appSetupMapBeans.getCommandBusDrivingAppMap();
  }

  @Bean
  public Map<Class<? extends AppCommand>, CommandBusDrivenFrm> makeCommandBusDrivenFrmMap(
      CommandBusDrivenFrm commandBusDrivenFrm) {

    Map<Class<? extends AppCommand>, CommandBusDrivenFrm> cbDrivenFrm_map = new HashMap<>();
    cbDrivenFrm_map.put(AppCommandSave.class, commandBusDrivenFrm);

    return cbDrivenFrm_map;
  }

  @Bean
  public Map<Class<?>, CommandHandlerDrivenFrm> makeCommandHandlerDrivenFrmMap(
      CommandHandlerDrivenFrm commandHandlerDrivenFrm) {

    Map<Class<?>, CommandHandlerDrivenFrm> chDrivenFrm_map = new HashMap<>();
    chDrivenFrm_map.put(DtoCommandSave.class, commandHandlerDrivenFrm);

    return chDrivenFrm_map;
  }

  @Bean
  public Map<Class<? extends Dto>, DtoSenderBus> makeDtoSenderBusMap() {
    Map<Class<? extends Dto>, DtoSenderBus> cbMap = new HashMap<>();
    DtoSenderBus dtoSenderBus = beanFactory.getBean(DtoSenderBusImpl.class);
    cbMap.put(DtoCommandSave.class, dtoSenderBus);

    return cbMap;
  }

  @Bean
  public Map<Class<? extends Dto>, DtoSenderHandler> makeDtoSenderHandlerMap() {
    Map<Class<? extends Dto>, DtoSenderHandler> chMap = new HashMap<>();
    chMap.put(DtoCommandSave.class, beanFactory.getBean(DtoSenderHandler000Impl.class));
    chMap.put(DtoCommandSave.class, beanFactory.getBean(DtoSenderHandler100Impl.class));

    return chMap;
  }
}
