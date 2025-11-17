package org.pokeherb.vendorservice.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("example-service")
public interface ExampleClient {
}
