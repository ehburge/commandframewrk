package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serial;
import java.io.Serializable;

@ToString
@Getter
@Jacksonized
@Builder
public class ThingDtoSave implements ThingDto, Serializable {
  @Serial private static final long serialVersionUID = -4730495382554054587L;

  private Long thingNbr;
  private DtoRole role;
  private ThingDtoComments comments;
  private ThingDtoParts parts;
}
