package org.example.springbootauth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAuthApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8888);
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8881);
    private static final String name = "Ann";
    private static final String password = "pass";
    private static final String[] authorities = new String[] { "READ" };
    private static HttpEntity<String> request;

    @BeforeAll
    public static void setUp() throws JSONException {
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("name", name);
        userDetailsRequestJson.put("password", password);
        userDetailsRequestJson.put("authorities", new JSONArray(authorities));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        request = new HttpEntity<>(userDetailsRequestJson.toString(), headers);

        devApp.start();
        prodApp.start();
    }

    @Test
    void devAppLoads() throws JSONException {
        restTemplate.postForEntity("http://localhost:" + devApp.getMappedPort(8888) + "/signIn", request, String.class);

        String baseUrl = "http://localhost:" + devApp.getMappedPort(8888) + "/authorize";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("name", name)
                .queryParam("password", password);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(builder.toUriString(), String.class);
        Assertions.assertEquals("[\"" + StringUtils.join(authorities, ",") + "\"]", forEntity.getBody());
    }

    @Test
    void prodAppLoads() {
        restTemplate.postForEntity("http://localhost:" + prodApp.getMappedPort(8881) + "/signIn", request, String.class);

        String baseUrl = "http://localhost:" + prodApp.getMappedPort(8881) + "/authorize";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("name", name)
                .queryParam("password", password);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(builder.toUriString(), String.class);
        Assertions.assertEquals("[\"" + StringUtils.join(authorities, ",") + "\"]", forEntity.getBody());
    }

}