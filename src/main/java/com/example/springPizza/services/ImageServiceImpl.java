package com.example.springPizza.services;

import com.example.springPizza.exceptions.ImageNotFoundException;
import com.example.springPizza.models.Image;
import com.example.springPizza.repositories.ImageRepository;
import com.example.springPizza.services.interfaces.ImageService;
import com.example.springPizza.utils.ImgurUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImgurUtil imgurUtil;

    @Override
    public Image createImage(MultipartFile multipartFile) {
        Image image = imgurUtil.saveImage(multipartFile);
        return imageRepository.saveAndFlush(image);
    }

    @Override
    public Image updateImage(Long id, MultipartFile newMultipartFile) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        image = imgurUtil.updateImage(newMultipartFile, image);
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        Image deleteImage = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        imgurUtil.deleteImage(deleteImage);
        imageRepository.deleteById(id);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }
}
