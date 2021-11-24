package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.persist.ThingComment;
import co.ex.frmwrk.gateway.persist.ThingPart;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ThingDtoSave implements ThingDto, Serializable {

  static final long serialVersionUID = 2217277525533320357L;

  private Long thingNbr;
  private String description;
  private String fullDescription;
  private List<ThingComment> comments;
  private List<ThingPart> parts;

}
