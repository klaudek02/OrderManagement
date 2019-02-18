package com.klaudek.ordermanagement.controller;

import com.klaudek.ordermanagement.domain.Purchase;
import com.klaudek.ordermanagement.domain.UpdatingAcceptedPurchaseException;
import com.klaudek.ordermanagement.resource.PurchaseResource;
import com.klaudek.ordermanagement.resource.PurchaseResourceAssembler;
import com.klaudek.ordermanagement.domain.ShippingAddress;
import com.klaudek.ordermanagement.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@ExposesResourceFor(Purchase.class)
@RestController
@RequestMapping(value="/purchases", produces = "application/json")
class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseResourceAssembler purchaseResourceAssembler;

    @GetMapping(path = "/add")
    public void addRecords()
    {
        List<Purchase> all = new ArrayList<>();
        for(long i = 0L; i < 10L; i++)
        {
            all.add(new Purchase("desc",i,new ShippingAddress("1","2 "," 3",",","4 ","5 ")));
        }
        purchaseRepository.saveAll(all);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PurchaseResource> createPurchase(@RequestBody Purchase purchase){
        Purchase createdPurchase = purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchaseResourceAssembler.toResource(createdPurchase), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Collection<PurchaseResource>>findAllPurchases() {

        List<Purchase> purchases = purchaseRepository.findAll();
        return new ResponseEntity<>(purchaseResourceAssembler.toResourceCollection(purchases), HttpStatus.OK);
    }
    @GetMapping(path = "/{purchaseId}")
    public ResponseEntity<PurchaseResource> findPurchaseById(@PathVariable Long purchaseId) {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

        if(purchase.isPresent())
        {
            return new ResponseEntity<>(purchaseResourceAssembler.toResource(purchase.get()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(path ="/{purchaseId}")
    public ResponseEntity<Void> deletePurchaseById(@PathVariable Long purchaseId) {

        HttpStatus responseStatus = purchaseRepository.deleteByPurchaseId(purchaseId) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }
    @Transactional
    @PostMapping(path ="/{purchaseId}", consumes="application/json")
    public ResponseEntity<PurchaseResource> updatePurchaseShippingAddress(@PathVariable Long purchaseId, @RequestBody ShippingAddress shippingAddress) throws UpdatingAcceptedPurchaseException {
        Optional<Purchase> updatedPurchase = purchaseRepository.findById(purchaseId);
        if(updatedPurchase.isPresent())
        {
            Purchase purchase = updatedPurchase.get();
            purchase.updateShippingData(shippingAddress);
            return new ResponseEntity<>(purchaseResourceAssembler.toResource(purchase), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @Transactional
    @PostMapping(path="/{purchaseId}/actions/accept")
    public ResponseEntity<PurchaseResource> acceptPurchase(@PathVariable Long purchaseId){
        Optional<Purchase> acceptedPurchase = purchaseRepository.findById(purchaseId);
        if(acceptedPurchase.isPresent())
        {
            Purchase purchase = acceptedPurchase.get();
            HttpStatus responseStatus = purchase.makeAccepted() ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
            return new ResponseEntity<>(purchaseResourceAssembler.toResource(purchase), responseStatus);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @PostMapping(path="/{purchaseId}/actions/complete")
    public ResponseEntity<PurchaseResource> completePurchase(@PathVariable Long purchaseId){
        Optional<Purchase> completedPurchase = purchaseRepository.findById(purchaseId);
        if(completedPurchase.isPresent())
        {
            Purchase purchase = completedPurchase.get();
            HttpStatus responseStatus = purchase.makeCompleted() ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
            return new ResponseEntity<>(purchaseResourceAssembler.toResource(purchase), responseStatus);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
