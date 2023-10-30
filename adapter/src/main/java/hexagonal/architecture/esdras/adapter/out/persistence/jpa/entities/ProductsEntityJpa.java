package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


    @Column(nullable = false)
    private String category;

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
