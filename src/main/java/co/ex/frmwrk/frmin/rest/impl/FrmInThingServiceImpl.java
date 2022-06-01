package co.ex.frmwrk.frmin.rest.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.app.driving.cmd.bus.CommandBusDrivingApp;
import co.ex.eventer.JsonMapper;
import co.ex.frmwrk.frmin.rest.FrmInThingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FrmInThingServiceImpl implements FrmInThingService {
  private static Logger LOGGER = LoggerFactory.getLogger( FrmInThingServiceImpl.class );

  private final AppSetupMapBeans setupMapBeans;

  @Override
  public void perform(AppCommand appCommand) {
    LOGGER.debug(
            "FrmInThingServiceImpl.thingCommand()"
                    .concat(System.lineSeparator())
                    .concat(JsonMapper.toJson(appCommand)));

    setupMapBeans.getCommandBusDrivingApp().perform(appCommand);
  }
}
