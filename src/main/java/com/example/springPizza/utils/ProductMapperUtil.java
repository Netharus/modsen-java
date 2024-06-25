package com.example.springPizza.utils;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Component
public class ProductMapperUtil {
    private final ImgurUtil imgurUtil;

    @Named(value = "mapImageToName")
    public String mapImageToName(MultipartFile image) throws Exception {
        return imgurUtil.saveImage(image);
    }
}
