package com.alc.gestock.services.strategy;

import com.alc.gestock.dto.UtilisateurDto;
import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidOperationException;
import com.alc.gestock.services.FlickrService;
import com.alc.gestock.services.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private FlickrService flickrService;
    private UtilisateurService utilisateurService;

    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }



    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

        String urlPhoto = flickrService.savePhoto(photo, titre);
        UtilisateurDto utilisateur = utilisateurService.findById(id);

        if(!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }

        utilisateur.setPhoto((urlPhoto));

        return utilisateurService.save(utilisateur);
    }
}
