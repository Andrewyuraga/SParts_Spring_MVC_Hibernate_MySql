package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yuraga
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PART")
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PART_ID", unique = true)
    private Long id;
    @Column(name = "PRODUCER", nullable = true)
    private String producer;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "CATEGORY", nullable = true)
    private String category;
    @Column(name = "NAME", nullable = true)
    private String name;
    @Column(name = "TYPE", nullable = true)
    private String type;
    @Column(name = "PRICE", nullable = true)
    private Double price;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "partId", fetch = FetchType.EAGER)
    private List<Basket> baskets;

    public Parts(String producer, String image, String category, String name, String type, Double price) {
        this.producer = producer;
        this.image = image;
        this.category = category;
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
