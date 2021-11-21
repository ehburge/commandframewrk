package co.ex.frmwrk.gateway.persist;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString(includeFieldNames=true)
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
  @Embedded
  @ElementCollection(fetch=FetchType.EAGER)
  @Column(name = "comments_col", nullable = false)
  @CollectionTable(name = "thing_comments", joinColumns = @JoinColumn(name = "thing_id"))
  private Set<String> comments;

  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(name = "thing_parts", joinColumns = @JoinColumn(name = "thing_id"))
  private Set<ThingPart> partSet;

  public ThingEntity() {}
}
