package co.ex.frmwrk.gateway.persist;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString(includeFieldNames=true)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ThingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private Long thingNbr;

  private String description;
  private String fullDescription;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_comments", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingComment> comments;

  @ElementCollection(fetch=FetchType.LAZY)
  @CollectionTable(name = "thing_parts", joinColumns = @JoinColumn(name = "thing_id"))
  private List<ThingPart> parts;

  public ThingEntity() {}
}
