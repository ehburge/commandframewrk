package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import com.ex.thing.model.AppRole;
import com.ex.thing.model.AppThingComments;
import com.ex.thing.model.AppThingParts;
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

  private Long thingNbr;
  private DtoRole role;
  private String eventKind;
  private String promoCodes;
  private String partsCompatibility;
  private ThingDtoComments comments;
  private ThingDtoParts parts;

}
