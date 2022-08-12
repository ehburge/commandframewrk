package co.ex.frmwrk.frmin.rest;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.eventer.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ThingController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThingController.class);

  private final FrmInThingService frmInThingService;
  private final FrmInThingToAppThingSaveMapper mapper;

  @GetMapping(value = "/")
  public String getthingCommand() {

    return "Hello";
  }

  @PutMapping("/thing")
  public String thingCommand(
      @RequestBody FrmInThingCommand thingIncoming,
      @RequestHeader(value = "Accept") String version) {

    LOGGER.debug(
        "ThingController.thingCommand()"
            .concat(System.lineSeparator())
            .concat("Accept=")
            .concat(version)
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingIncoming)));

    AppThingCommandSave thingCommandSave = mapper.frmInThingToAppThing(thingIncoming);

    frmInThingService.perform(thingCommandSave);

    return "Sent Thing command " + thingIncoming.getUuid();
  }
}
