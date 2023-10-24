package co.ex.frmwrk.gateway.impl;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder(access = AccessLevel.PUBLIC)
public class DtoRole {

  private final Long actorId;
  private final String groupId;
  private final String loyaltyCode;
}
