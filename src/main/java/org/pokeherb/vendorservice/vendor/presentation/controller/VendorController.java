package org.pokeherb.vendorservice.vendor.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.pokeherb.vendorservice.global.infrastructure.CustomResponse;
import org.pokeherb.vendorservice.global.infrastructure.success.GeneralSuccessCode;
import org.pokeherb.vendorservice.vendor.domain.VendorDetailsRepository;
import org.pokeherb.vendorservice.vendor.domain.dto.VendorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vendor")
public class VendorController {

    private final VendorDetailsRepository vendorDetailsRepository;

    @GetMapping("/{vendorId}")
    public CustomResponse<?> getVendor(@PathVariable("vendorId") UUID vendorId) {

        VendorDto vendorDto = vendorDetailsRepository.findById(vendorId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, vendorDto);
    }

    @GetMapping("/hub")
    public CustomResponse<?> getAllVendorsByHub(
            @RequestParam(name = "hubId") Long hubId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<VendorDto> pageResult = vendorDetailsRepository.findAllByHubId(hubId, pageable);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK, pageResult);
    }

}
