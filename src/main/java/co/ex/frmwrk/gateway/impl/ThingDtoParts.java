package co.ex.frmwrk.gateway.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  private String partsCompatibility;

  @JsonProperty("parts")
  private List<ThingDtoPart> parts;
}
