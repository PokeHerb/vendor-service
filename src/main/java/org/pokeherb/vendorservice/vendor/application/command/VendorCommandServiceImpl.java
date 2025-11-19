package org.pokeherb.vendorservice.vendor.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pokeherb.vendorservice.global.infrastructure.client.HubServiceClient;
import org.pokeherb.vendorservice.global.infrastructure.exception.CustomException;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorCreateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorUpdateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.response.VendorBasicResponseDto;
import org.pokeherb.vendorservice.vendor.domain.VendorRepository;
import org.pokeherb.vendorservice.vendor.domain.entity.Vendor;
import org.pokeherb.vendorservice.vendor.domain.exception.VendorErrorCode;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VendorCommandServiceImpl implements VendorCommandService {

    private final VendorRepository vendorRepository;
    private final HubServiceClient hubServiceClient;

    @Override
    public VendorBasicResponseDto createVendor(VendorCreateRequestDto dto) {

        // 도메인 쪽에서 구성하도록 변경하기
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

        return VendorBasicResponseDto.builder()
                .vendorId(savedVendor.getId())
                .hudId(savedVendor.getHubId())
                .name(savedVendor.getName())
                .build();
    }

    @Override
    public VendorBasicResponseDto updateVendor(VendorUpdateRequestDto dto) {

        UUID vendorId = dto.vendorId();
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() ->
                new CustomException(VendorErrorCode.VENDOR_NOT_FOUND));

        vendor.existsById(dto.hubId(), hubServiceClient);
        vendor.changeInfo(dto);
        Vendor updateVendor = vendorRepository.save(vendor);

        return VendorBasicResponseDto.from(updateVendor);

    }

    @Override
    public void deleteVendor(String username, UUID vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new CustomException(VendorErrorCode.VENDOR_NOT_FOUND));

        vendor.delete(username);

    }
}
