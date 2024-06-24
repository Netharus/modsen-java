package com.example.springPizza.controllers;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImgurObject {
    private MultipartFile multipartFile;
}
