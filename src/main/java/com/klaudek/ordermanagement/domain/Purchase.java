package com.klaudek.ordermanagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    @Getter private Long purchaseId;
    @Getter private Long userId;
    @Getter private String description;
    @Getter private boolean accepted;
    @Getter private boolean completed;
    @Getter private LocalDateTime purchaseDate;
    @Getter @Setter private LocalDateTime acceptPurchaseDate;
    @Getter @Setter private LocalDateTime finishPurchaseDate;
    @Getter private long purchaseCost;
    @Getter private ShippingAddress shippingAddress;

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
}
