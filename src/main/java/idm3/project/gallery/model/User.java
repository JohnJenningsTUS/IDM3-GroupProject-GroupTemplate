package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private long userId;
    @Column(name = "FirstName", length=50)
    private String firstName;
    @Column(name = "surname", nullable=true, length=225)
    private String surname;
    @Column(name = "emailaddress", length=100)
    private String emailAddress;
    @Column(name = "usertype", length=50)
    private String userType;
    @Column(name = "username",length=50)
    private String userName;
    @Column(name = "password", nullable=true, length=225)
    private String password;
    @Column(name = "organization", nullable=true, length=225)
    private String organization;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userType='" + userType + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
