package com.alc.gestock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.alc.gestock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/photos")
public interface PhotoApi {

    @PostMapping(value = APP_ROOT + "/photos/{id}/{title}/{context}")
    @ApiOperation(value = "Enregistrer une photo", notes = "permet d'enregisdtrer ou modifier une photo")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "La photo a ete enregistre"),
            @ApiResponse(code = 400 , message = "L'objet n'est pas valide")
    })
    Object savePhoto(@PathVariable("context") String context, Integer id, @RequestPart("file") MultipartFile photo, @PathVariable("title") String title) throws IOException, FlickrException;
}
