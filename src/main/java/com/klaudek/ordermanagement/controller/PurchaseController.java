package com.klaudek.ordermanagement.controller;

import com.klaudek.ordermanagement.domain.Purchase;
import com.klaudek.ordermanagement.domain.PurchaseResource;
import com.klaudek.ordermanagement.domain.PurchaseResourceAssembler;
import com.klaudek.ordermanagement.domain.ShippingAddress;
import com.klaudek.ordermanagement.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*")
@ExposesResourceFor(Purchase.class)
@RestController
@RequestMapping(value="/purchase", produces = "application/json")
class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseResourceAssembler purchaseResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public void addRecords()
    {
        List<Purchase> all = new ArrayList<>();
        for(long i = 0L; i < 10L; i++)
        {
            all.add(new Purchase("desc",i,new ShippingAddress("1","2 "," 3",",","4 ","5 ")));
        }
        purchaseRepository.saveAll(all);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<PurchaseResource>> findAllPurchases() {

        List<Purchase> purchases = purchaseRepository.findAll();
        return new ResponseEntity<>(purchaseResourceAssembler.toResourceCollection(purchases), HttpStatus.OK);
    }
}
