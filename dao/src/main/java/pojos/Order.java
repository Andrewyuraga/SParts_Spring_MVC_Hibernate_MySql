package pojos;

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
@Table(name = "ORDERS")
@DynamicInsert
@DynamicUpdate
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", unique = true)
    private Long id;
    @Column(name = "ORDER_DATE")
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
    @Column(name = "TOTAL", nullable = true)
    private Double total;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
