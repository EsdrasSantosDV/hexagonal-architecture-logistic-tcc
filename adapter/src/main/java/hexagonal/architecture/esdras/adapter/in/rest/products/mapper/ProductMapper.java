package hexagonal.architecture.esdras.adapter.in.rest.products.mapper;


import hexagonal.architecture.esdras.adapter.in.rest.products.dto.ProductDto;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.ProductCategoryDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;

public class ProductMapper {

    public static ProductDomain dtoToDomain(ProductDto dto) {
        return new ProductDomain(
                ProductIdDomain.randomProductIdDomain(),
                dto.getName(),
                dto.getDescription(),
                ProductCategoryDomain.valueOf(dto.getCategory().toUpperCase()),
                dto.getHeight(),
                dto.getWidth(),
                dto.getDepth(),
                dto.getStorageInstructions(),
                dto.getRestrictions()
        );
    }


}