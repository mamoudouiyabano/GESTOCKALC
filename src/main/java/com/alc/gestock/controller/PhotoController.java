package com.alc.gestock.controller;

import com.alc.gestock.controller.api.PhotoApi;
import com.alc.gestock.services.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class PhotoController implements PhotoApi {

    private StrategyPhotoContext strategyPhotoContext;


    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context,id,photo.getInputStream(),title);
    }
}
