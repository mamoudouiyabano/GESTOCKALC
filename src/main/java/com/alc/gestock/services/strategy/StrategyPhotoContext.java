package com.alc.gestock.services.strategy;

import com.alc.gestock.exception.ErrorCodes;
import com.alc.gestock.exception.InvalidOperationException;
import com.flickr4java.flickr.FlickrException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private  Strategy strategy;


    @Setter
    private String context;


    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;

    }

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id,photo,title);
    }


    private  void determinContext(String context) {
        final String beanName = context + "Strategy";

        switch (context) {
            case "employe":
               strategy = beanFactory.getBean("beanName",SaveEmployePhoto.class);
                break;

            case "utilisateur":
                strategy = beanFactory.getBean("beanName",SaveUtilisateurPhoto.class);
                break;
             default: throw new InvalidOperationException("Contexte inconnue", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }

}
