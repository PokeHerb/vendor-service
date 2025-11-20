package org.pokeherb.vendorservice.vendor.application.dto.request;

import lombok.Builder;
import org.pokeherb.vendorservice.vendor.domain.entity.VendorType;

@Builder
public record VendorCreateRequestDto(
   Long hubId,
   String name,
   String description,
   String tel,
   VendorType vendorType,

   String sido,
   String sigungu,
   String eupmyeon,
   String dong,
   String ri,
   String street,
   String buildingNo,
   String details
) {
}
