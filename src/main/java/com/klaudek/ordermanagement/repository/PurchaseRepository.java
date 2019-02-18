package com.klaudek.ordermanagement.repository;

import com.klaudek.ordermanagement.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    boolean deleteByPurchaseId(Long purchaseId);

}
