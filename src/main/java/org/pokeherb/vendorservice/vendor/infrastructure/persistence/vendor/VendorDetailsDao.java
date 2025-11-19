package org.pokeherb.vendorservice.vendor.infrastructure.persistence.vendor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.pokeherb.vendorservice.global.infrastructure.exception.CustomException;
import org.pokeherb.vendorservice.vendor.domain.VendorDetailsRepository;
import org.pokeherb.vendorservice.vendor.domain.dto.VendorDto;
import org.pokeherb.vendorservice.vendor.domain.entity.QVendor;
import org.pokeherb.vendorservice.vendor.domain.entity.Vendor;
import org.pokeherb.vendorservice.vendor.domain.exception.VendorErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VendorDetailsDao implements VendorDetailsRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public VendorDto findById(UUID vendorId) {

        QVendor qVendor = QVendor.vendor;

        Vendor vendor = queryFactory.selectFrom(qVendor)
                .where(qVendor.id.eq(vendorId)
                        ,qVendor.deletedAt.isNull())
                .fetchFirst();
        if (vendor == null) throw new CustomException(VendorErrorCode.VENDOR_NOT_FOUND);

        return vendor.toDto();
    }

    @Override
    public Page<VendorDto> findAllByHubId(UUID hubId, Pageable pageable) {

        // 리스트 조회
        QVendor qVendor = QVendor.vendor;
        List<Vendor> vendors = queryFactory
                .selectFrom(qVendor)
                .where(qVendor.hubId.eq(hubId), qVendor.deletedAt.isNull())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qVendor.createdAt.desc())
                .fetch();

        // count 쿼리
        JPAQuery<Long> countQuery =
                queryFactory.select(qVendor.count())
                        .from(qVendor)
                        .where(qVendor.hubId.eq(hubId), qVendor.deletedAt.isNull());

        List<VendorDto> vendorDtoList = vendors.stream()
                .map(Vendor::toDto)
                .toList();

        return PageableExecutionUtils.getPage(vendorDtoList, pageable, countQuery::fetchOne);
    }
}
