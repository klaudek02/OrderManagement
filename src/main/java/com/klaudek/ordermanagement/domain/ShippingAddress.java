package com.klaudek.ordermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Embeddable
public class ShippingAddress {
    @Getter @Setter private String customerName;
    @Getter @Setter private String street;
    @Getter @Setter private String street2;
    @Getter @Setter private String city;
    @Getter @Setter private String zipCode;
    @Getter @Setter private String country;

}
