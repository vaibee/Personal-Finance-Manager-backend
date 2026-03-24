package com.finance.personal_finance_manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiInsightService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public AiInsightService() {
        this.restTemplate = new RestTemplate();
    }

    public String generateInsights(String json) {

        String prompt =
                "You are an expert personal finance advisor.\n\n" +
                        "Analyze the following financial data in JSON format:\n" +
                        json +
                        "\n\nProvide:\n" +
                        "1. Spending trends\n" +
                        "2. Overspending warnings\n" +
                        "3. Budget health\n" +
                        "4. Savings opportunities\n" +
                        "5. Two actionable recommendations.\n";

        // Build Gemini request body
        Map<String, Object> textPart = Map.of("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(textPart));

        Map<String, Object> request = new HashMap<>();
        request.put("contents", List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        try {
            Map<String, Object> response =
                    restTemplate.postForObject(url, entity, Map.class);

            // Extract AI text:
            Map firstCandidate = ((List<Map>) response.get("candidates")).get(0);
            Map contentMap = (Map) firstCandidate.get("content");
            List<Map> parts = (List<Map>) contentMap.get("parts");
            String aiText = (String) parts.get(0).get("text");

            return aiText;

        } catch (Exception e) {
            e.printStackTrace();
            return "AI_ERROR: " + e.getMessage();
        }
    }
}