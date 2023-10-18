package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.ProductCategoryDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ProductDomain
 * responsible for abstracting the product used in the logistics process
 *
 * @author esdras
 */
@Data
@AllArgsConstructor
@Builder
public class ProductDomain {
    private final ProductIdDomain id;
    private String name;
    private String description;
    private ProductCategoryDomain category;
    private MoneyDomain price;
    private Double height;
    private Double width;
    private Double depth;
    private String storageInstructions;
    private String restrictions;


}
