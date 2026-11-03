package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectID")
    private long projectId;
    @Column(name = "ProjectName")
    private String projectName;
    @Column(name = "ProjectDescription",length=500)
    private String projectDescription;
    //@Column(name = "UserID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID")
    private User user;
    @Column(name = "Category")
    private String category;
    @Column(name = "ProjectDescriptionSummary")
    private String projectDescriptionSummary;
    @Column(name = "ProjectHeroImage")
    private String projectHeroImage;
    @Column(name = "CreationDate")
    private java.sql.Date creationDate;
    @Column(name = "additionalDoc")
    private String additionalDoc;

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", user=" + user +
                ", category='" + category + '\'' +
                ", projectDescriptionSummary='" + projectDescriptionSummary + '\'' +
                ", projectHeroImage='" + projectHeroImage + '\'' +
                ", creationDate=" + creationDate +
                ", additionalDoc='" + additionalDoc + '\'' +
                '}';
    }
}
