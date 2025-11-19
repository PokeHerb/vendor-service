package org.pokeherb.vendorservice.vendor.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pokeherb.vendorservice.global.domain.Auditable;
import org.pokeherb.vendorservice.global.infrastructure.client.HubServiceClient;
import org.pokeherb.vendorservice.global.infrastructure.exception.CustomException;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorUpdateRequestDto;
import org.pokeherb.vendorservice.vendor.domain.exception.VendorErrorCode;

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
    private Vendor(UUID hubId, String name, String description, String tel, VendorType vendorType, String sido, String sigungu, String eupmyeon, String dong, String ri, String street,String buildingNo, String details){
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

    public void changeInfo(VendorUpdateRequestDto dto) {
        this.hubId = dto.hubId();
        this.name = dto.name();
        this.tel = dto.tel();
        this.description = dto.description();
        this.vendorType = dto.vendorType();
        setAddress(dto.sido(), dto.sigungu(), dto.eupmyeon(), dto.dong(), dto.ri(), dto.street(), dto.buildingNo(), dto.details());
    }

    public boolean existsById(UUID hubId, HubServiceClient client) {

        if (!client.existsHub(hubId)) {
            throw new CustomException(VendorErrorCode.HUB_NOT_FOUND);
        }
        return true;
    }

    public void delete(String username) {
        softDelete(username);
    }



}
