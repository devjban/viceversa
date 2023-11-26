package com.example.viceversa.sevice;

import com.example.viceversa.entity.GalleryItem;
import com.example.viceversa.repository.GalleryItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {
    @Value("${data-go-kr.api.end-point}")
    private String URL;

    @Value("${data-go-kr.api.service-key}")
    private String SERVICE_KEY;

    private final GalleryItemRepository galleryItemRepository;
    @PostConstruct
    public void init() throws JsonProcessingException {
        WebClient client = getWebClient();
        JSONObject body = getBody(client);
        save(body);
    }

    private WebClient getWebClient() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(URL)
                .build();
    }

    private JSONObject getBody(WebClient client) {
        String result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("numOfRows", 100)
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "TEST")
                        .queryParam("_type", "json")
                        .queryParam("serviceKey", SERVICE_KEY)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject = new JSONObject(result);
        JSONObject response = jsonObject.getJSONObject("response");
        return response.getJSONObject("body");
    }

    private void save(JSONObject body) throws JsonProcessingException {
        if (!body.isEmpty()) {
            JSONObject items = body.getJSONObject("items");
            JSONArray item = items.getJSONArray("item");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<GalleryItem> galleryItems = mapper.readValue(item.toString(),TypeFactory.defaultInstance().constructCollectionType(List.class, GalleryItem.class));

            galleryItemRepository.saveAll(galleryItems);
        }
    }
}
