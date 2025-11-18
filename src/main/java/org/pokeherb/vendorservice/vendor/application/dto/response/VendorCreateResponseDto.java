package org.pokeherb.vendorservice.vendor.application.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record VendorCreateResponseDto(
        UUID vendorId,
        UUID hudId,
        String name

) {
}
