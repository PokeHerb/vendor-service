package org.pokeherb.vendorservice.vendor.presentation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pokeherb.vendorservice.vendor.domain.VendorDetailsRepository;
import org.pokeherb.vendorservice.vendor.domain.dto.VendorDto;
import org.pokeherb.vendorservice.vendor.domain.entity.VendorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @MockitoBean
    private VendorDetailsRepository vendorDetailsRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("업체 단일 조회")
    void getVendor() throws Exception {
        UUID vendorId = UUID.randomUUID();
        Long hubId = 1L;

        // 가짜 반환값 생성 (DB에 저장하지 않음, 메모리상에만 존재)
        VendorDto mockResponse = VendorDto.builder()
                .id(vendorId)
                .hubId(hubId)
                .name("업체111")
                .vendorType(VendorType.PRODUCTION)
                .createdAt(LocalDateTime.now())
                .build();

        // "findById가 호출되면, 위의 mockResponse를 리턴해라"라고 가짜 행동 정의 (Stubbing)
        given(vendorDetailsRepository.findById(vendorId)).willReturn(mockResponse);

        // when (실행) & then (검증)
        mockMvc.perform(get("/v1/vendor/{vendorId}", vendorId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(vendorId.toString()))
                .andExpect(jsonPath("$.result.name").value("업체111"));
    }


    @Test
    @DisplayName("업체 목록 페이징 조회")
    void getAllVendorsByHub() throws Exception {

        Long hubId = 1L;

        List<VendorDto> dtoList = List.of(
                VendorDto.builder().name("업체A").build(),
                VendorDto.builder().name("업체B").build()
        );

        PageImpl<VendorDto> page = new PageImpl<>(dtoList, PageRequest.of(0, 10), 2);

        given(vendorDetailsRepository.findAllByHubId(eq(hubId), any(Pageable.class))).willReturn(page);

        mockMvc.perform(get("/v1/vendor/hub")
                        .param("hubId", hubId.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content[0].name").value("업체A"))
                .andExpect(jsonPath("$.result.totalElements").value(2));
    }
}