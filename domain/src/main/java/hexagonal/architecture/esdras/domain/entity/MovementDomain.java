package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.MovementTypeDomain;
import hexagonal.architecture.esdras.domain.vo.MovimentationIdentityDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class MovementDomain {
    private final MovimentationIdentityDomain id;
    private ProductDomain product;
    private MovementTypeDomain type;
    private Integer quantity;
    private Date dateOfMovement;

    private void setType(MovementTypeDomain type) {
        if (type == null) {
            throw new IllegalArgumentException("Movement type cannot be null");
        }
        this.type = type;
    }

    private void setQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity should be positive");
        }
        this.quantity = quantity;
    }

    private void setDateOfMovement(Date dateOfMovement) {
        if (dateOfMovement.after(new Date())) {
            throw new IllegalArgumentException("Date of movement cannot be in the future");
        }
        this.dateOfMovement = dateOfMovement;
    }

    public boolean isEntry() {
        return this.type == MovementTypeDomain.ENTRY;
    }

    public boolean isOutgoing() {
        return this.type == MovementTypeDomain.OUTGOING;
    }

    public boolean isLoss() {
        return this.type == MovementTypeDomain.LOSS;
    }

}
