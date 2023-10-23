package co.ex.frmwrk.frmin.rest.impl;


import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.driving.bus.CommandBusDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInCommand;
import co.ex.frmwrk.frmin.rest.FrmInThingService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FrmInThingServiceImpl implements FrmInThingService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FrmInThingServiceImpl.class);

  private final Map<Class<?>, CommandBusDrivingFrmAdapter> commandBusDrivingFrmAdapterMap;

  @Override
  public void perform(FrmInCommand frmInCommand) {
    LOGGER.debug(
        "FrmInThingServiceImpl.thingCommand()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(frmInCommand)));

    commandBusDrivingFrmAdapterMap.get(frmInCommand.getClass()).perform(frmInCommand);
  }
}
