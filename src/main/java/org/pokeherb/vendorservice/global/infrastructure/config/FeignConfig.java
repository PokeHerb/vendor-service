package org.pokeherb.vendorservice.global.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.pokeherb.vendorservice.global.infrastructure.client")
public class FeignConfig {
}
