package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.ItemStockIdentifyDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemStockDomain {

    private final ItemStockIdentifyDomain id;

    private ProductDomain product;

    private Integer quantity;


    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to increase should be positive");
        }
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to decrease should be positive");
        }
        this.quantity -= amount;
    }

    public ProductIdDomain getProductId() {
        return this.product.getId();
    }

}
