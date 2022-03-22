package co.ex.frmwrk.gateway.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@Jacksonized
@Builder(access = AccessLevel.PUBLIC)
public class ThingDtoComments implements Serializable {
  @JsonProperty("commants")
  private List<String> comments;
}
