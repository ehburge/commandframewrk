package co.ex.frmwrk.frmin.rest;

import co.ex.frmwrk.gateway.impl.DtoRole;
import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@ToString
@Getter
@Jacksonized
@Builder
public class FrmInThingCommand {
  private UUID uuid;
  private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private ThingDtoComments comments;
  private ThingDtoParts parts;
}
