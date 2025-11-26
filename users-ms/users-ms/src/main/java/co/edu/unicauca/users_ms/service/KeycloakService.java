package co.edu.unicauca.users_ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class KeycloakService {


    @Autowired
    private RestTemplate restTemplate;

    private final String serverUrl = "http://localhost:8080";
    private final String realm = "usuarios";
    private final String adminUser = "lefo";
    private final String adminPass = "123ASD.";
    private final String clientId = "admin-cli";


    public void registrarEnKeycloak(String correo, String password, String rol,String nombre,String apellido) throws Exception {
        String token = obtenerTokenAdmin();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> user = new HashMap<>();
        user.put("username", correo);
        user.put("email", correo);
        user.put("enabled", true);
        user.put("emailVerified", true);
        user.put("requiredActions", new ArrayList<String>());
        user.put("firstName", nombre);
        user.put("lastName", apellido);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", password);
        credentials.put("temporary", false);

        user.put("credentials", List.of(credentials));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                serverUrl + "/admin/realms/" + realm + "/users",
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error creando usuario en Keycloak: " + response.getBody());
        }

        // Obtener ID del usuario
        ResponseEntity<List> userSearch = restTemplate.exchange(
                serverUrl + "/admin/realms/" + realm + "/users?username=" + correo,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                List.class
        );

        Map<String, Object> userInfo = (Map<String, Object>) userSearch.getBody().get(0);
        String userId = (String) userInfo.get("id");


        asignarRol(token, userId, rol);
    }
    public String solicitarToken(String username, String password) throws Exception {

        String tokenUrl = "http://localhost:8080/realms/usuarios/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "users-ms-client");
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                tokenUrl,
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();

        if (responseBody == null || responseBody.get("access_token") == null) {
            throw new Exception("Error credenciales invalidas.");
        }

        return (String) responseBody.get("access_token");

    }

    public String obtenerTokenAdmin() {

        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("grant_type", "password");
        body.add("username", adminUser);
        body.add("password", adminPass);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            Map<String, Object> mapa = response.getBody();

            if (mapa == null || mapa.get("access_token") == null) {
                throw new RuntimeException("Keycloak no devolvió access_token. Respuesta: " + mapa);
            }

            return mapa.get("access_token").toString();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error de autenticación con Keycloak: " + e.getResponseBodyAsString(), e);
        }
    }

    private void asignarRol(String token, String userId, String rol) {
        System.out.println("Intentando asignar rol: " + rol + " al usuario: " + userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {

            String clientId = "users-ms-client";
            ResponseEntity<Map<String, Object>> roleResponse = restTemplate.exchange(
                    serverUrl + "/admin/realms/" + realm + "/clients/" + getClientUuid(token, clientId) + "/roles/" + rol,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            if (roleResponse.getBody() == null) {
                System.err.println("El rol no existe en el client: " + rol);
                throw new RuntimeException("El rol no existe en el client: " + rol);
            }

            Map<String, Object> roleRepresentation = roleResponse.getBody();
            System.out.println("Rol encontrado en client: " + roleRepresentation);

            // 2. Asignar el rol del client al usuario
            HttpEntity<List<Map<String, Object>>> request =
                    new HttpEntity<>(List.of(roleRepresentation), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    serverUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/clients/" + getClientUuid(token, clientId),
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Rol del client asignado exitosamente: " + rol);
            } else {
                System.err.println("Error asignando rol del client. Status: " + response.getStatusCode());
                System.err.println("Response: " + response.getBody());
            }

        } catch (Exception e) {
            System.err.println("Error asignando rol del client: " + e.getMessage());
            throw new RuntimeException("Error asignando rol: " + e.getMessage(), e);
        }
    }


    private String getClientUuid(String token, String clientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                serverUrl + "/admin/realms/" + realm + "/clients?clientId=" + clientId,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        if (response.getBody() != null && !response.getBody().isEmpty()) {
            return (String) response.getBody().get(0).get("id");
        }
        throw new RuntimeException("Client no encontrado: " + clientId);
    }
}