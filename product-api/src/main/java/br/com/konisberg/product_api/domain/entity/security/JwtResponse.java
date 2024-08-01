package br.com.konisberg.product_api.domain.entity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Integer id;
    private String name;
    private String email;

    public static JwtResponse getUser(Claims jwtClaims) {
        try {
            return new ObjectMapper().convertValue(jwtClaims.get("authUser"), JwtResponse.class);
        } catch (Exception ex) {
            System.err.println("An unexpected error occurred while parsing JWT claims: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

}
