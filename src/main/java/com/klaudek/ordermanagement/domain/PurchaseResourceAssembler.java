package com.klaudek.ordermanagement.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class PurchaseResourceAssembler extends ResourceAssembler<Purchase, PurchaseResource> {

    @Autowired
    protected EntityLinks entityLinks;

    @Override
    public PurchaseResource toResource(Purchase purchase) {

        PurchaseResource resource = new PurchaseResource(purchase);

        final Link selfLink = entityLinks.linkToSingleResource(Purchase.class, purchase);

        resource.add(selfLink.withSelfRel());

        return resource;
    }
}
