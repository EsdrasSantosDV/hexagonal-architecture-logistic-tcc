package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

/*
 * StockDomain
 * ADICIONAR O PRODUTO EM ESTOQUE SE AINDA NÃO ESTIVER , CASO CONTRÁRIO, AUMENTAR A QUANTIDADE EM ESTOQUE
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class StockDomain {

    private final StockIdentityDomain id;
    private final String name;
    private Map<ProductIdDomain, ItemStockDomain> stock = new LinkedHashMap<>();
    private List<MovementDomain> movements = new ArrayList<>();

    public void movementEntry(ProductDomain product, int quantity) {
        if (stock == null) {
            stock = new LinkedHashMap<>();
        }
        ItemStockDomain item = stock.get(product.getId());
        if (item == null) {
            item = ItemStockDomain.builder()
                    .id(ItemStockIdentifyDomain.generateRandomPart())
                    .product(product)
                    .quantity(quantity)
                    .build();
            stock.put(product.getId(), item);
        } else {
            item.increaseQuantity(quantity);
        }

        MovementDomain movement = MovementDomain.builder()
                .id(new MovimentationIdentityDomain(name))
                .product(product)
                .type(MovementTypeDomain.ENTRY)
                .quantity(quantity)
                .dateOfMovement(new Date())
                .build();
        movements.add(movement);
    }

    public void movementExit(ProductDomain product, int quantity) {
        if (stock == null) {
            stock = new LinkedHashMap<>();
        }
        ItemStockDomain item = stock.get(product.getId());
        if (item == null) {
            item = ItemStockDomain.builder()
                    .id(ItemStockIdentifyDomain.generateRandomPart())
                    .product(product)
                    .quantity(quantity)
                    .build();
            stock.put(product.getId(), item);
        } else {
            item.decreaseQuantity(quantity);
        }

        MovementDomain movement = MovementDomain.builder()
                .id(new MovimentationIdentityDomain(name))
                .product(product)
                .type(MovementTypeDomain.OUTGOING)
                .quantity(quantity)
                .dateOfMovement(new Date())
                .build();
        movements.add(movement);
    }


}
