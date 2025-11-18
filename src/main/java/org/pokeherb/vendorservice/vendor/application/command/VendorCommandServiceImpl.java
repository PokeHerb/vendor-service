package org.pokeherb.vendorservice.vendor.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorCreateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.response.VendorCreateResponseDto;
import org.pokeherb.vendorservice.vendor.domain.VendorRepository;
import org.pokeherb.vendorservice.vendor.domain.entity.Vendor;
import org.pokeherb.vendorservice.vendor.domain.entity.VendorAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VendorCommandServiceImpl implements VendorCommandService {

    private final VendorRepository vendorRepository;

    @Override
    public VendorCreateResponseDto createVendor(VendorCreateRequestDto dto) {


        Vendor newVendor = Vendor.builder()
                .hubId(dto.hubId())
                .name(dto.name())
                .description(dto.description())
                .tel(dto.tel())
                .description(dto.description())
                .vendorType(dto.vendorType())
                .sido(dto.sido())
                .sigungu(dto.sigungu())
                .eupmyeon(dto.eupmyeon())
                .dong(dto.dong())
                .ri(dto.ri())
                .street(dto.street())
                .buildingNo(dto.buildingNo())
                .details(dto.details())
                .build();

        Vendor savedVendor = vendorRepository.save(newVendor);

        return VendorCreateResponseDto.builder()
                .vendorId(savedVendor.getId())
                .hudId(savedVendor.getHubId())
                .name(savedVendor.getName())
                .build();
    }
}
