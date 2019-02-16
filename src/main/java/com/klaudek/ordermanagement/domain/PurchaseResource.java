package com.klaudek.ordermanagement.domain;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

public class PurchaseResource extends ResourceSupport {

    @Getter private final long purchaseId;
    @Getter private final Long userId;
    @Getter private final String description;
    @Getter private final boolean accepted;
    @Getter private final boolean completed;
    @Getter private final LocalDateTime purchaseDate;
    @Getter private final LocalDateTime acceptPurchaseDate;
    @Getter private final LocalDateTime finishPurchaseDate;
    @Getter private final long purchaseCost;
    @Getter private final ShippingAddress shippingAddress;

    public PurchaseResource(Purchase purchase) {
        this.purchaseId = purchase.getPurchaseId();
        this.userId = purchase.getUserId();
        this.description = purchase.getDescription();
        this.accepted = purchase.isAccepted();
        this.completed = purchase.isCompleted();
        this.purchaseDate = purchase.getPurchaseDate();
        this.acceptPurchaseDate = purchase.getAcceptPurchaseDate();
        this.finishPurchaseDate = purchase.getFinishPurchaseDate();
        this.purchaseCost = purchase.getPurchaseCost();
        this.shippingAddress = purchase.getShippingAddress();
    }
}
