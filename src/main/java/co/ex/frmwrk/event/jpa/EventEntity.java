package co.ex.frmwrk.event.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Getter
public class EventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private Long thingNbr;

  private String description;
  private String fullDescription;
  private BigDecimal price;

  protected EventEntity() {}
}
