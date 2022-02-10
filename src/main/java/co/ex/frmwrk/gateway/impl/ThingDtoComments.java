package co.ex.frmwrk.gateway.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@ToString
@Getter
@Jacksonized
@Builder(access = AccessLevel.PUBLIC)
public class ThingDtoComments {
  private List<String> comments;
}
