package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@ToString(includeFieldNames=true)
@Builder
@Getter
@Entity
public class ThingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private Long thingNbr;

  private Timestamp dttm;

  private String description;
  private String fullDescription;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_comments", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingComment> comments;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_parts", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingDtoPart> parts;
}
