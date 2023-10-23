package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "produto_core")
@Getter
@Setter
public class ProductCoreEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
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
    private NfInvoiceEntryEntityJpa nf_entry;

    @ManyToOne
    private ProductsEntityJpa product;


    private int quantity;

}
