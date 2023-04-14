package co.ex.frmwrk.frmin.rest.impl;

import co.ex.eventer.JsonMapper;
import co.ex.frmwrk.driving.bus.CommandBusDrivingFrmAdapter;
import co.ex.frmwrk.driving.handler.CommandHandlerDrivingFrmAdapter;
import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;
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
  public void perform(FrmInThingCommand frmInThingCommand) {
    LOGGER.debug(
        "FrmInThingServiceImpl.thingCommand()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(frmInThingCommand)));

    commandBusDrivingFrmAdapterMap.get(frmInThingCommand.getClass()).perform(frmInThingCommand);
  }
}
