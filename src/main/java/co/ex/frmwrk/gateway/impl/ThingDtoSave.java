package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.UUID;

@ToString
@Getter
@Jacksonized
@Builder
public class ThingDtoSave implements ThingDto, Serializable {

  private UUID uuid;
  @Setter private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private String partsCompatibility;
  private ThingDtoComments comments;
  private ThingDtoParts parts;
}
