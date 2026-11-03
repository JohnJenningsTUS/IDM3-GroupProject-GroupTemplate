package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="showcase")
public class Showcase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ShowcaseId")
  private long showcaseId;
  @Column(name = "Name")
  private String name;
  @Column(name = "Description")
  private String description;
  @Column(name = "Image")
  private String image;
  @Column(name = "Status")
  private String status;


}
