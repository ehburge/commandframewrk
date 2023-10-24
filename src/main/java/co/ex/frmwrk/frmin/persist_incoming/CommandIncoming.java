package co.ex.frmwrk.frmin.persist_incoming;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@ToString(includeFieldNames = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "command_incoming", indexes = @Index(columnList = "commandNbr, dttm asc"))
public class CommandIncoming {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false, updatable = false, unique = true)
  private String uuid;

  // IDENTITY uses underlying DB id generation, AUTO uses hibernate id generation
  @Column(nullable = false, updatable = false, unique = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "command_incoming_seq")
  @SequenceGenerator(name = "command_incoming_seq", allocationSize = 5)
  private Long commandNbr;

  @CreationTimestamp private Timestamp dttm;

  private String content_type; // data class

  @Column(length = 5000)
  private String entity_content; // Command json
}
