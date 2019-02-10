package pojos;

import enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Class UserDao
 * <p>
 * Created by Yuraga 25/12/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = {"id", "login", "address"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private Long id;
    @Column(name = "LOGIN", unique = true, nullable = true, length = 50)
    @Pattern(regexp = "^[A-Z]+[a-z]+$", message = "Login must be alphanumeric with no spaces and first capital")
    private String login;
    @Column(name = "PASSWORD", nullable = true)
    private String password;
    @Column(name = "ADDRESS", nullable = true, length = 190)
    private String address;
    @Column(name = "USER_RIGHTS", columnDefinition = "CHAR(6) default 'USER'")
    private String right;
    @Column(nullable = false)
    private String state = State.ACTIVE.getState();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userId")
    private List<Basket> baskets;
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "userId")
    private List<Review> reviews;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userId")
    private List<Order> orders;

    public User(String login, String password, String address) {
        this.login = login;
        this.password = password;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
