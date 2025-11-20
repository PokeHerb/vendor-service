package org.pokeherb.vendorservice.vendor.domain.dto;

import lombok.Builder;
import org.pokeherb.vendorservice.vendor.domain.entity.VendorType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record VendorDto(
        UUID id,
        Long hubId,
        String name,
        String description,
        String tel,
        VendorType vendorType,
        String street,
        String details,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) implements Serializable {}
