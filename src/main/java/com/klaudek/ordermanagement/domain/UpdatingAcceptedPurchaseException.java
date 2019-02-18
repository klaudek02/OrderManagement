package com.klaudek.ordermanagement.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED, reason = "Accepted Purchase cannot be updated")
public class UpdatingAcceptedPurchaseException extends Exception {
    public UpdatingAcceptedPurchaseException(String errorMessage) {
        super(errorMessage);
    }
}
