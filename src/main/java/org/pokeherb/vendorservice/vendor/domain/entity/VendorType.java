package org.pokeherb.vendorservice.vendor.domain.entity;

import lombok.Getter;

@Getter
public enum VendorType {

    PRODUCTION("생산"),
    RECEIPT("수령"),
    ;

    private String vendor;

    VendorType(String vendor) {

        this.vendor = vendor;
    }
}
