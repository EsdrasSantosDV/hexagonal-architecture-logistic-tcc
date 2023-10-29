package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stock")
@Getter
@Setter
public class StockEntityJpa {
    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovementEntityJpa> movements = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemStockEntityJpa> items = new HashSet<>();
}
