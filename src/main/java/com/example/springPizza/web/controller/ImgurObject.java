package com.example.springPizza.web.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImgurObject {
    private MultipartFile multipartFile;
}
