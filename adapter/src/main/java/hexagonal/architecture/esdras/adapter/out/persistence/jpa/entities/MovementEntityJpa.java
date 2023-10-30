package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "movement_stock")
@Getter
@Setter
public class MovementEntityJpa {

    @Id
    private String id;
    
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "date_of_movement", nullable = false)
    private Date dateOfMovement;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private StockEntityJpa stock;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductsEntityJpa product;

}
