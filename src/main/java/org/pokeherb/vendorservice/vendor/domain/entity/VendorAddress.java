package org.pokeherb.vendorservice.vendor.domain.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VendorAddress {

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 100, nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String address;

    public VendorAddress(String city, String street, String zipcode, String address) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.address = address;
    }

}
