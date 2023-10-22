package hexagonal.architecture.esdras.adapter.out.persistence.jpa;


import hexagonal.architecture.esdras.domain.vo.ProductCategoryDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductsEntityJpa {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategoryDomain category;

    @Column(nullable = true)
    private Double height;

    @Column(nullable = true)
    private Double width;

    @Column(nullable = true)
    private Double depth;

    @Column(name = "storage_instructions", nullable = true)
    private String storageInstructions;

    @Column(nullable = true)
    private String restrictions;


}
