package co.ex.frmwrk.gateway.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
@AllArgsConstructor
@Getter
public class ThingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private Long thingNbr;

  private String description;
  private String fullDescription;
  private BigDecimal price;

  public ThingEntity() {}
}
