package hexagonal.architecture.esdras.adapter.in.rest.products.mapper;


import hexagonal.architecture.esdras.adapter.in.rest.products.dto.ProductDto;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.ProductCategoryDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;

public class ProductMapper {

    public static ProductDomain dtoToDomain(ProductDto dto) {
        return new ProductDomain(
                ProductIdDomain.randomProductIdDomain(),
                dto.getName(),
                dto.getDescription(),
                ProductCategoryDomain.valueOf(dto.getCategory().toUpperCase()),
                new MoneyDomain(dto.getCurrency(), dto.getAmount()),
                dto.getHeight(),
                dto.getWidth(),
                dto.getDepth(),
                dto.getStorageInstructions(),
                dto.getRestrictions()
        );
    }

    public static ProductDto domainToDto(ProductDomain domain) {
        ProductDto dto = new ProductDto();
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setCategory(domain.getCategory().name());
        dto.setCurrency(domain.getPrice().currency());
        dto.setHeight(domain.getHeight());
        dto.setWidth(domain.getWidth());
        dto.setDepth(domain.getDepth());
        dto.setStorageInstructions(domain.getStorageInstructions());
        dto.setRestrictions(domain.getRestrictions());
        return dto;
    }


}