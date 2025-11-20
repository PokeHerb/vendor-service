package org.pokeherb.vendorservice.global.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("hub-service")
public interface HubServiceClient {

    @GetMapping("/v1/hub/{hubId}/exists")
    boolean existsHub(@PathVariable("hubId") Long hubId);
}
