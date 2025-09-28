package com.dimas519.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApigwApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigwApplication.class, args);
    }

}


@RestController
public class APIGatewayController {

    private final RestClient rest = RestClient.builder().build();

    @GetMapping("/APIGW")
    public Map<String, Object> getTime(@RequestParam(defaultValue = "Asia/Jakarta") String tz) {
        // Hindari URLEncoder; pecah path jadi segment agar tidak double-encode
        String[] parts = tz.split("/"); // contoh: ["Asia","Tokyo"]
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://worldtimeapi.org/api/timezone")
                .pathSegment(parts) // aman untuk path
                .build(true)
                .toUri();

        JsonNode node = rest.get().uri(uri).retrieve().body(JsonNode.class);

        String timezone = node.path("timezone").asText(tz);
        String datetime = node.path("datetime").isMissingNode() ? null : node.get("datetime").asText();

        return Map.of(
                "timezone", timezone,
                "datetime", datetime,
                "source", "worldtimeapi.org",
                "rawExample", node
        );
    }
}

