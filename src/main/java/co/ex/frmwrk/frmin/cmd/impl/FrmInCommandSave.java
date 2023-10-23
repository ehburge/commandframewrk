package co.ex.frmwrk.frmin.cmd.impl;

import co.ex.frmwrk.frmin.cmd.FrmInCommand;import co.ex.frmwrk.gateway.impl.DtoRole;
import co.ex.frmwrk.gateway.impl.DtoComments;
import co.ex.frmwrk.gateway.impl.DtoParts;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;import java.util.UUID;

@ToString
@Getter
@Jacksonized
@Builder
public class FrmInCommandSave implements FrmInCommand {

  private UUID uuid;
  private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private DtoComments comments;
  private DtoParts parts;
}
