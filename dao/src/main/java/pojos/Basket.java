package pojos;

import enums.Condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Yuraga
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BASKET")
@DynamicInsert
@DynamicUpdate
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BASKET_ID", unique = true)
    private Long id;
    @Column(name = "BASKET_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User userId;
    @ManyToOne
    @JoinColumn(name = "PART_ID", foreignKey = @ForeignKey(name = "PART_ID_FK"))
    private Parts partId;
    @Column(name = "QUANTITY", columnDefinition = "INT default 1")
    private Integer quantity;
    private String condition = Condition.ACTIVE.getCondition();
    @Column(name = "COST", nullable = true)
    private Double cost;

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
