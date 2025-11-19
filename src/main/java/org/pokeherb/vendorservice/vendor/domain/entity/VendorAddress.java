package org.pokeherb.vendorservice.vendor.domain.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VendorAddress {

    // 시/도
    private String sido;
    // 시/군/구
    private String sigungu;
    // 읍/면
    private String eupmyeon;
    // 동
    private String dong;
    // 리
    private String ri;
    // 도로명 주소
    private String street;
    // 건물 번호
    private String building_no;
    // 나머지 상세 주소
    private String details;

    @Builder
    protected VendorAddress(String sido, String sigungu, String eupmyeon, String dong, String ri, String street, String building_no, String details) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyeon = eupmyeon;
        this.dong = dong;
        this.ri = ri;
        this.street = street;
        this.building_no = building_no;
        this.details = details;
    }

}
