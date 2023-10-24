package co.ex.frmwrk.frmin.rest;


import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.frmin.cmd.impl.FrmInCommandSave;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommandController {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommandController.class);

  private final FrmInCommandService frmInCommandService;

  @GetMapping(value = "/")
  public String getcommandCommand() {

    return "Hello";
  }

  @PutMapping("/command")
  public String commandCommand(
      @RequestBody FrmInCommandSave commandIncoming,
      @RequestHeader(value = "Accept") String version) {

    LOGGER.debug(
        "CommandController.commandCommand()"
            .concat(System.lineSeparator())
            .concat("Accept=")
            .concat(version)
            .concat(System.lineSeparator())
            .concat(JsonMapper.toJson(commandIncoming)));


    frmInCommandService.perform(commandIncoming);

    return "Sent Command command " + commandIncoming.getId();
  }
}
