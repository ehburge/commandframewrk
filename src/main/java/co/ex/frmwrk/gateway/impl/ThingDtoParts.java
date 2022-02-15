package co.ex.frmwrk.gateway.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@ToString
@Getter
@Jacksonized
@Builder
public class ThingDtoParts {
  private List<ThingDtoPart> parts;
}
