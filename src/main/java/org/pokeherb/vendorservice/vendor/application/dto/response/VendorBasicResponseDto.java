package org.pokeherb.vendorservice.vendor.application.dto.response;

import lombok.Builder;
import org.pokeherb.vendorservice.vendor.domain.entity.Vendor;

import java.util.UUID;

@Builder
public record VendorBasicResponseDto(
        UUID vendorId,
        Long hudId,
        String name

) {

    public static VendorBasicResponseDto from(Vendor updateVendor) {
        return VendorBasicResponseDto.builder()
                .vendorId(updateVendor.getId())
                .hudId(updateVendor.getHubId())
                .name(updateVendor.getName())
                .build();
    }
}
