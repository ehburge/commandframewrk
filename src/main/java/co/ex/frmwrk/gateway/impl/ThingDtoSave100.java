package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@ToString
@Getter
@Jacksonized
@Builder
public class ThingDtoSave100 implements ThingDto {

  private UUID uuid;
  @Setter private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private ThingDtoComments comments;
  private ThingDtoParts parts;
}