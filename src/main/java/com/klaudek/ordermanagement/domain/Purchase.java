package com.klaudek.ordermanagement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@Entity
@Table
public class Purchase implements Identifiable<Long> {

    @Id
    @GeneratedValue
    @Getter private Long purchaseId;
    @Getter @Setter private Long userId;
    @Getter @Setter private String description;
    @Getter @Setter private boolean accepted;
    @Getter @Setter private boolean completed;
    @Getter @Setter private LocalDateTime purchaseDate;
    @Getter @Setter private LocalDateTime acceptPurchaseDate;
    @Getter @Setter private LocalDateTime finishPurchaseDate;
    @Getter @Setter private long purchaseCost;
    @Getter @Setter private ShippingAddress shippingAddress;

    public Purchase(String description, Long orderCost, ShippingAddress shippingAddress) {
        this(description, orderCost, shippingAddress, null);
    }
    public Purchase(String description, Long orderCost, ShippingAddress shippingAddress, Long userId) {
        this.description = description;
        this.purchaseCost = orderCost;
        this.shippingAddress = shippingAddress;
        this.purchaseDate = LocalDateTime.now();
        this.userId = userId;

        this.accepted = false;
        this.completed = false;
        this.acceptPurchaseDate = null;
        this.finishPurchaseDate = null;
    }

    public void setAccepted() {
        this.accepted = true;
    }
    public void setCompleted()
    {
        this.completed = true;
    }
    public void updateShippingData(ShippingAddress shippingAddress) {
        if(!accepted)
            this.shippingAddress = shippingAddress;
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public Long getId() {
        return purchaseId;
    }
    public void setId(Long purchaseId)
    {
        this.purchaseId = purchaseId;
    }
}
