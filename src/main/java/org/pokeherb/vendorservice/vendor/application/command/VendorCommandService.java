package org.pokeherb.vendorservice.vendor.application.command;

import org.pokeherb.vendorservice.vendor.application.dto.request.VendorCreateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.response.VendorCreateResponseDto;


public interface VendorCommandService {

    VendorCreateResponseDto createVendor(VendorCreateRequestDto dto);


}
