package org.pokeherb.vendorservice.vendor.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.pokeherb.vendorservice.global.infrastructure.error.BaseErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum VendorErrorCode implements BaseErrorCode {

    VENDOR_NOT_FOUND(HttpStatus.NOT_FOUND, "VENDOR404", "관련 업체가 존재하지 않습니다."),
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "HUB404", "관련 허브가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
