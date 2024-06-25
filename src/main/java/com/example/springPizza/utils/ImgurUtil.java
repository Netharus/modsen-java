package com.example.springPizza.utils;

import com.example.springPizza.configs.ImgurProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class ImgurUtil {
    private final ImgurProperties imgurProperties;
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String saveImage(MultipartFile multipartFile) throws Exception {
        ResponseEntity<String> imgurResponse = restTemplateBuilder.build()
                .exchange("https://api.imgur.com/3/image",
                        HttpMethod.POST,
                        RequestEntity.post("https://api.imgur.com/3/image")
                                .header("Authorization", "Client-ID " + imgurProperties.getClientId())
                                .body(multipartFile.getBytes()),
                        String.class);

        if (imgurResponse.getStatusCode().isError()) {
            // TODO: 24.06.2024 поменять исключение
            throw new Exception();
        }

        JsonNode rootNode = objectMapper.readTree(imgurResponse.getBody());
        return rootNode.path("data").path("link").asText();
    }
}


//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.set("Authorization", "Client-ID " + imgurProperties.getClientId());
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("image", multipartFile.getBytes());
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange("https://api.imgur.com/3/image", HttpMethod.POST, requestEntity, String.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            String imageUrl = response.getBody();
//            System.out.println("Image uploaded to Imgur: " + imageUrl);
//        } else {
//            System.out.println("Failed to upload image to Imgur. Status code: " + response.getStatusCode());
//        }