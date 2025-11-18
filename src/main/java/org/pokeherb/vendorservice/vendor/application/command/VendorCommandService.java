package org.pokeherb.vendorservice.vendor.application.command;

import org.pokeherb.vendorservice.vendor.application.dto.request.VendorUpdateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorCreateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.response.VendorBasicResponseDto;


public interface VendorCommandService {

    VendorBasicResponseDto createVendor(VendorCreateRequestDto dto);

    VendorBasicResponseDto updateVendor(VendorUpdateRequestDto dto);




}
