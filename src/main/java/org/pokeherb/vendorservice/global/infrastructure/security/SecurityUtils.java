package org.pokeherb.vendorservice.global.infrastructure.security;

public interface SecurityUtils {

    boolean isPermitted(String role);

    String getCurrentUsername();
}
