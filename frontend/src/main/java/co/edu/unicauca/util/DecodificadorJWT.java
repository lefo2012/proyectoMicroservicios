package co.edu.unicauca.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class DecodificadorJWT {

    public static JsonNode decodeJwt(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Token JWT inválido");
        }

        // decodificar payload (parte 2)
        byte[] decodedBytes = Base64.getUrlDecoder().decode(parts[1]);
        String payloadJson = new String(decodedBytes, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(payloadJson);  // <-- payload es JSON válido
    }
    public static List<String> getRoles(String token) throws Exception {

        JsonNode root = decodeJwt(token); // <-- NO usar readTree aquí

        JsonNode rolesNode = root.path("resource_access")
                .path("users-ms-client")
                .path("roles");

        List<String> roles = new ArrayList<>();

        if (rolesNode.isArray()) {
            for (JsonNode role : rolesNode) {
                roles.add(role.asText());
            }
        }

        return roles;
    }
}
