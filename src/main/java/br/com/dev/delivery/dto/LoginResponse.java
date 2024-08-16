package br.com.dev.delivery.dto;

public record LoginResponse(String accessToken, Long expiresIn, String sub, String scope, String name, String email) {
}
