package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="showcaseproject")
public class Showcaseproject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ShowcaseProjectID")
  private long showcaseProjectId;
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ProjectId")
  private Project projectId;
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ShowcaseID")
  private Showcase showcaseId;


}
