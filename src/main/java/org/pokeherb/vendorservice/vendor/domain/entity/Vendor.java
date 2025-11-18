package org.pokeherb.vendorservice.vendor.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pokeherb.vendorservice.global.domain.Auditable;
import org.pokeherb.vendorservice.global.infrastructure.client.HubServiceClient;

import java.util.UUID;

@Getter
@Entity
@Table(name = "p_vendor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Vendor extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID hubId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VendorType vendorType;

    @Embedded
    private VendorAddress address;

    @Builder
    public Vendor(UUID hubId, String name, String description, String tel, VendorType vendorType, String sido, String sigungu, String eupmyeon, String dong, String ri, String street,String buildingNo, String details){
        this.hubId = hubId;
        this.name = name;
        this.description = description;
        this.tel = tel;
        this.vendorType = vendorType;
        setAddress(sido, sigungu, eupmyeon, dong, ri, street, buildingNo, details);
    }

    private void setAddress(String sido, String sigungu, String eupmyeon, String dong, String ri, String street,String buildingNo, String details) {
        this.address = new VendorAddress(sido, sigungu, eupmyeon, dong, ri, street, buildingNo, details);
    }

    public void changeInfo(String name, String tel, String description, String sido, String sigungu, String eupmyeon, String dong, String ri, String street,String buildingNo, String details) {
        this.name = name;
        this.tel = tel;
        this.description = description;
        setAddress(sido, sigungu, eupmyeon, dong, ri, street, buildingNo, details);
    }

    public boolean existsById(UUID hubId, HubServiceClient client) {

        if (client.existsHub(hubId)) {
            return true;
        }
        return false;
    }



}
