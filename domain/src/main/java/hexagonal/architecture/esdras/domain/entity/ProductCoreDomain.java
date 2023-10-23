package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.SkuProductDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductCoreDomain {

    private SkuProductDomain sku;

    private ProductDomain product;

    private MoneyDomain price;

    private Integer quantity;

    private Date entryDate;

    private Date dueDate;


    public void increaseQuantityBy(int augend) {
        this.quantity += augend;
    }

    public void dateNow() {
        this.entryDate = new Date();
    }

    public void generateSkuFromProduct() {
        this.sku = SkuProductDomain.fromProductDomain(product);
    }

    public boolean isSameProductSku(ProductCoreDomain productCoreDomain) {
        return this.sku.equals(productCoreDomain.sku);
    }

    public boolean isSameProductDomain(ProductDomain productDomain) {
        return this.sku.equals(SkuProductDomain.fromProductDomain(productDomain));
    }

    public boolean isDueDateBefore(Date date) {
        return this.dueDate.before(date);
    }

    public boolean isDueDateAfter(Date date) {
        return this.dueDate.after(date);
    }

    public boolean isDueDateBetween(Date startDate, Date endDate) {
        return this.dueDate.after(startDate) && this.dueDate.before(endDate);
    }

    public boolean isEntryDateBefore(Date date) {
        return this.entryDate.before(date);
    }

    public boolean isEntryDateAfter(Date date) {
        return this.entryDate.after(date);
    }

    public long numberOfDaysToExpire() {
        LocalDateTime startDateTime = entryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dueDateTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Duration duration = Duration.between(startDateTime, dueDateTime);
        return duration.toDays();
    }

    public boolean isQuantityGreaterThanZero() {
        return this.quantity > 0;
    }

    public boolean isQuantityLessThanZero() {
        return this.quantity < 0;
    }

    public boolean isQuantityEqualToZero() {
        return this.quantity == 0;
    }

    public boolean isQuantityGreaterThan(Integer quantity) {
        return this.quantity > quantity;
    }

    public boolean isQuantityLessThan(Integer quantity) {
        return this.quantity < quantity;
    }

    public boolean isQuantityEqualTo(Integer quantity) {
        return Objects.equals(this.quantity, quantity);
    }

    public boolean isQuantityBetween(Integer startQuantity, Integer endQuantity) {
        return this.quantity > startQuantity && this.quantity < endQuantity;
    }

    public MoneyDomain calculateTotalPrice() {
        return this.price.multiply(this.quantity);
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
