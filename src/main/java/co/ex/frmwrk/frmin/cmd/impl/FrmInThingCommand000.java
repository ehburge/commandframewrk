package co.ex.frmwrk.frmin.cmd.impl;

import co.ex.frmwrk.frmin.cmd.FrmInThingCommand;import co.ex.frmwrk.gateway.impl.DtoRole;
import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;import java.util.UUID;

@ToString
@Getter
@Jacksonized
@Builder
public class FrmInThingCommand000 implements FrmInThingCommand {

  private UUID uuid;
  private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private ThingDtoComments comments;
  private ThingDtoParts parts;
}
