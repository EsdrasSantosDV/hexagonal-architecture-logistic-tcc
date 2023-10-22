package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice_entry")
@Getter
@Setter
public class NfInvoiceEntryEntityJpa {
    @Id
    private int id;

    @Column(nullable = false)
    private Date issueDate;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String issuer;

    @Column(nullable = false)
    private String priceCurrency;

    @Column(nullable = false)
    private BigDecimal priceAmount;

    @OneToMany(mappedBy = "invoice_entry_products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductsEntityJpa> products;


}
