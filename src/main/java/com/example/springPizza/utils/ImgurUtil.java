package com.example.springPizza.utils;

import com.example.springPizza.configs.ImgurProperties;
import com.example.springPizza.models.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ImgurUtil {
    private final ImgurProperties imgurProperties;
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO: custom errors
    public Image saveImage(MultipartFile multipartFile) {
        try {
            ResponseEntity<String> imgurResponse = restTemplateBuilder.build()
                    .exchange("https://api.imgur.com/3/image",
                            HttpMethod.POST,
                            RequestEntity.post("https://api.imgur.com/3/image")
                                    .header("Authorization", "Client-ID " + imgurProperties.getClientId())
                                    .body(multipartFile.getBytes()),
                            String.class);

            if (imgurResponse.getStatusCode().isError()) {
                throw new RuntimeException();
            }

            JsonNode rootNode = objectMapper.readTree(imgurResponse.getBody());
            String url = rootNode.path("data").path("link").asText();
            String deleteHash = rootNode.path("data").path("deletehash").asText();

            return Image.builder()
                    .url(url)
                    .deleteHash(deleteHash)
                    .build();

        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException();
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
    }

    public Image updateImage(MultipartFile newMultipartFile, Image oldImage) {
        Image newImage = saveImage(newMultipartFile);
        deleteImage(oldImage);

        oldImage.setUrl(newImage.getUrl());
        oldImage.setDeleteHash(newImage.getDeleteHash());

        return oldImage;
    }

    //TODO: custom errors
    public void deleteImage(Image image) {
        ResponseEntity<String> imgurResponse = restTemplateBuilder.build()
                .exchange(RequestEntity.delete("https://api.imgur.com/3/image/" + image.getDeleteHash())
                                .header("Authorization", "Client-ID " + imgurProperties.getClientId())
                                .build(),
                        String.class);

        if (imgurResponse.getStatusCode().isError()) {
            throw new RuntimeException();
        }
    }
}
