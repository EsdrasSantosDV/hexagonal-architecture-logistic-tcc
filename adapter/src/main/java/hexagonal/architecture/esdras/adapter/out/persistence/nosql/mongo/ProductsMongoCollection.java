package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
@MongoEntity(collection = "product_collection")
public class ProductsMongoCollection extends PanacheMongoEntity {
    public ObjectId id;
    private String name;
    private String description;
    private String category;
    private String priceCurrency;
    private BigDecimal priceAmount;
    private Double height;
    private Double width;
    private Double depth;
    private String storageInstructions;
    private String restrictions;
}
