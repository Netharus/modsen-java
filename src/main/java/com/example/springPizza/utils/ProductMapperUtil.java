package com.example.springPizza.utils;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ProductMapperUtil {
    @Named(value = "mapPhotoToName")
    public String mapPhotoToName(MultipartFile photo) {
        // TODO: 19.06.2024
        return null;
    }

    @Named(value = "mapNameToPhoto")
    public MultipartFile mapNameToPhoto(String photoName) {
        // TODO: 19.06.2024
        return null;
    }
}
