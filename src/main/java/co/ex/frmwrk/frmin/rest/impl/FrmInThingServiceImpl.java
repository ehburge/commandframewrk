package co.ex.frmwrk.frmin.rest.impl;

import co.ex.app.cmd.AppCommand;
import co.ex.app.config.AppSetupMapBeans;
import co.ex.frmwrk.frmin.rest.FrmInThingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FrmInThingServiceImpl implements FrmInThingService {

  private final AppSetupMapBeans setupMapBeans;

  @Override
  public void perform(AppCommand appCommand) {
    setupMapBeans.getCommandBusDrivingApp().perform(appCommand);
  }
}
