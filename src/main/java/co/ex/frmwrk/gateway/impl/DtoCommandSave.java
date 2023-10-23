package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.Dto;
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
public class DtoCommandSave implements Dto {

  private UUID uuid;
  @Setter private Long id;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private DtoComments comments;
  private DtoParts parts;

  @Override
  public Long getId() {
    return id;
  }
}
