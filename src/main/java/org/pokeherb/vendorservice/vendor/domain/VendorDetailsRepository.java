package org.pokeherb.vendorservice.vendor.domain;

import org.pokeherb.vendorservice.vendor.domain.dto.VendorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VendorDetailsRepository {

    VendorDto findById(UUID vendorId);

    Page<VendorDto> findAllByHubId(Long hubId, Pageable pageable);
}
