package com.alc.gestock.services.strategy;

import com.alc.gestock.dto.EmployeDto;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.services.EmployeService;
import com.alc.gestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("employeStrategy")
@Slf4j
public class SaveEmployePhoto implements Strategy<EmployeDto> {

    private FlickrService flickrService;
    private EmployeService employeService;

    public SaveEmployePhoto(FlickrService flickrService, EmployeService employeService){
        this.flickrService = flickrService;
        this.employeService = employeService;
    }

    @Override
    public EmployeDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EmployeDto employe = employeService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);

        if(!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }

        employe.setPhoto((urlPhoto));

        return employeService.save(employe);
    }
}
