package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "produto_core")
@Getter
@Setter
public class ProdutoCoreEntityJpa {

    @Id
    private String sku;

    @Column(nullable = false)
    private String priceCurrency;

    @Column(nullable = false)
    private BigDecimal priceAmount;


    @Column(nullable = false)
    private Date entryDate;

    @Column(nullable = false)
    private Date dueDate;

    @ManyToOne
    private CartJpaEntity cart;

    @ManyToOne
    private ProductJpaEntity product;


}
