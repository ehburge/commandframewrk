package co.ex.frmwrk.frmin.rest;

import co.ex.app.cmd.impl.AppThingCommandSave;
import co.ex.eventer.event.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ThingController {
  private static Logger LOGGER = LoggerFactory.getLogger(ThingController.class);

  private final FrmInThingService frmInThingService;
  private final FrmInThingToAppThingSaveMapper mapper;

  @GetMapping(value = "/")
  public String getthingCommand() {

    return "Hello";
  }

  @PutMapping("/thing")
  public String thingCommand(@RequestBody FrmInThingCommand thingIncoming) {

    LOGGER.debug(
        "ThingController.thingCommand()"
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingIncoming)));

    AppThingCommandSave thingCommandSave = mapper.frmInThingToAppThing(thingIncoming);

    frmInThingService.perform(thingCommandSave);

    return "Sent Thing command " + thingIncoming.getUuid();
  }
}
