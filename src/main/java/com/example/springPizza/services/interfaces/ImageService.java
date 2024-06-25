package com.example.springPizza.services.interfaces;

import com.example.springPizza.models.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image createImage(MultipartFile multipartFile);
    Image updateImage(Long id, MultipartFile newMultipartFile);
    void deleteImage(Long id);
    Image getImageById(Long id);
}
