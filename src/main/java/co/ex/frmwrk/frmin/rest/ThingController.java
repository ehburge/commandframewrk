package co.ex.frmwrk.frmin.rest;


import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.frmin.cmd.impl.FrmInThingCommand000;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ThingController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThingController.class);

  private final FrmInThingService frmInThingService;

  @GetMapping(value = "/")
  public String getthingCommand() {

    return "Hello";
  }

  @PutMapping("/thing")
  public String thingCommand(
      @RequestBody FrmInThingCommand000 thingIncoming,
      @RequestHeader(value = "Accept") String version) {

    LOGGER.debug(
        "ThingController.thingCommand()"
            .concat(System.lineSeparator())
            .concat("Accept=")
            .concat(version)
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(thingIncoming)));


    frmInThingService.perform(thingIncoming);

    return "Sent Thing command " + thingIncoming.getThingNbr();
  }
}
