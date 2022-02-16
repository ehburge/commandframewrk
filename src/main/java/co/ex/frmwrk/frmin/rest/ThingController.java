package co.ex.frmwrk.frmin.rest;

import com.ex.thing.cmd.impl.AppThingCommandSave;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ThingController {

  private final FrmInThingService frmInThingService;
  private final FrmInThingToAppThingSaveMapper mapper;

  @GetMapping(value = "/")
  public String getthingCommand() {

    return "Hello";
  }

  @PutMapping("/thing")
  public String thingCommand(@RequestBody FrmInThingCommand thingIncoming) {

    AppThingCommandSave thingCommandSave = mapper.frmInThingToAppThing(thingIncoming);
    frmInThingService.perform(thingCommandSave);

    return "Sent Thing command " + thingIncoming.getUuid();
  }
}
