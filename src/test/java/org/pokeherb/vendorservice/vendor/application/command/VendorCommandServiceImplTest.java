package org.pokeherb.vendorservice.vendor.application.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pokeherb.vendorservice.global.infrastructure.exception.CustomException;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorCreateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.request.VendorUpdateRequestDto;
import org.pokeherb.vendorservice.vendor.application.dto.response.VendorBasicResponseDto;
import org.pokeherb.vendorservice.vendor.domain.VendorRepository;
import org.pokeherb.vendorservice.vendor.domain.entity.Vendor;
import org.pokeherb.vendorservice.vendor.domain.entity.VendorType;
import org.pokeherb.vendorservice.vendor.domain.exception.VendorErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class VendorCommandServiceImplTest {


    @Autowired
    private VendorCommandService vendorCommandService;

    @Autowired
    private VendorRepository vendorRepository;

    VendorCreateRequestDto request;

    @BeforeEach
    void init() {

        request = VendorCreateRequestDto.builder()
                .hubId(UUID.fromString("8f2c4b68-9a34-4a7d-b2c3-91f23f0e91b9"))
                .name("테스트이름")
                .description("식료품 업체")
                .tel("010-1234-5678")
                .vendorType(VendorType.PRODUCTION)
                .sido("서울시")
                .sigungu("종로구")
                .eupmyeon(null)
                .dong(null)
                .ri(null)
                .street("김수로")
                .buildingNo("12345")
                .details("1111111동 123호")
                .build();
    }

    @Test
    @Transactional
    @DisplayName("업체 생성 서비스")
    void createVendor() {

        VendorBasicResponseDto response = vendorCommandService.createVendor(request);

        Vendor vendor = vendorRepository.findById(response.vendorId()).orElseThrow();

        assertEquals("테스트이름", vendor.getName());
        assertEquals("010-1234-5678", vendor.getTel());

    }

    @Test
    @Transactional
    @DisplayName("업체 수정 서비스")
    void updateVendor() {

        Vendor vendor = Vendor.builder()
                .hubId(UUID.fromString("d1b7e2c0-4d9a-41a0-8b2e-5a3d58c4a2f0"))
                .name("abc")
                .description("전자기기")
                .tel("010-0101-0101")
                .vendorType(VendorType.PRODUCTION)
                .build();

        vendorRepository.save(vendor);

        VendorUpdateRequestDto correctRequest = VendorUpdateRequestDto.builder()
                .vendorId(vendor.getId())
                .hubId(UUID.fromString("d1b7e2c0-4d9a-41a0-8b2e-5a3d58c4a2f0"))
                .name("abcabc")
                .description("컴퓨터 전문업")
                .tel("010-0101-0101")
                .vendorType(VendorType.PRODUCTION)
                .build();

        VendorBasicResponseDto response = vendorCommandService.updateVendor(correctRequest);

        assertThat(response.hudId()).isEqualTo(correctRequest.hubId());
        assertThat(response.name()).isEqualTo("abcabc");

        VendorUpdateRequestDto wrongRequest = VendorUpdateRequestDto.builder()
                .vendorId(vendor.getId())
                .hubId(UUID.fromString("d1b7e2c0-4d9a-41a0-8b2e-5a3d58c4a2f0"))
                .name("abcabc")
                .description("컴퓨터 전문업")
                .tel("010-0101-0101")
                .vendorType(VendorType.PRODUCTION)
                .build();
    }

    @Test
    @Transactional
    @DisplayName("업체 삭제 서비스")
//    @WithMockUser(username = "test_user")
    void deleteVendor() {

        VendorBasicResponseDto response = vendorCommandService.createVendor(request);
        UUID vendorId = response.vendorId();

        assertThrows(CustomException.class, () -> {
            vendorCommandService.deleteVendor("a", UUID.fromString("d1b7e2c0-4d9a-41a0-8b2e-5a3d58c4a2f0"));
        });
        assertThat(vendorRepository.findById(vendorId)).isPresent();

    }
}