package org.backendDevTest.infra.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MockRepository {
    private final Map<String, String> productBodies = new HashMap<>();
    private final Map<String, String> similarIdsBodies = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/mocks.json");
        JsonNode root = mapper.readTree(is);

        for (JsonNode node : root) {
            String path = node.get("path").asText();
            String body = node.has("body") ? node.get("body").asText() : null;

            if (path.endsWith("/similarids")) {
                similarIdsBodies.put(path.split("/product/")[1].replace("/similarids",""), body);
            } else if (path.matches("/product/\\d+")) {
                productBodies.put(path.split("/product/")[1], body);
            }
        }
    }

    public String getProduct(String id) {
        return productBodies.get(id);
    }

    public String getSimilarIds(String id) {
        return similarIdsBodies.get(id);
    }
}
