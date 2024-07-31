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

//    public static JwtResponse getUser(Claims jwtClaims) {
//        try {
//            Object id = jwtClaims.get("id");
//            Object name = jwtClaims.get("name");
//            Object email = jwtClaims.get("email");
//
//            if (id instanceof Integer && name instanceof String && email instanceof String) {
//                return JwtResponse.builder()
//                        .id((Integer) id)
//                        .name((String) name)
//                        .email((String) email)
//                        .build();
//            } else {
//                System.err.println("Type mismatch in JWT claims.");
//                return null;
//            }
//        } catch (ClassCastException e) {
//            System.err.println("ClassCastException occurred while parsing JWT claims: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            System.err.println("An unexpected error occurred while parsing JWT claims: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }

}
