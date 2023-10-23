package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonId;

@Getter
@Setter
@ToString
@MongoEntity(collection = "products")
public class ProductsMongoCollection extends PanacheMongoEntityBase {
    @BsonId
    private String id;
    private String name;
    private String description;
    private String category;
    private Double height;
    private Double width;
    private Double depth;
    private String storageInstructions;
    private String restrictions;
}
