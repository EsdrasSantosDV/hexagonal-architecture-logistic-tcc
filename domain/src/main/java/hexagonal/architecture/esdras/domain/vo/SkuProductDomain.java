package hexagonal.architecture.esdras.domain.vo;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.Objects;

/**
 * @param value - value of the SkuProductDomain
 *              responsible for indentify the product sku used in the logistics process
 */
public record SkuProductDomain(String value) {

    public SkuProductDomain {
        Objects.requireNonNull(value, "'valor' não pode ser nulo");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'valor' não pode ser vazio");
        }
    }

    public static SkuProductDomain fromProductDomain(ProductDomain product) {
        String namePrefix = product.getName().substring(0, 2).toUpperCase();
        String categoryPrefix = product.getCategory().toString().substring(0, 2).toUpperCase();
        String dimensions = String.format("%02d%02d%02d",
                product.getHeight().intValue(),
                product.getWidth().intValue(),
                product.getDepth().intValue());

        return new SkuProductDomain(namePrefix + categoryPrefix + dimensions);
    }


}
