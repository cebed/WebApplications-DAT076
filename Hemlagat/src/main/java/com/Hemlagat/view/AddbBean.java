package com.Hemlagat.view;

import com.Hemlagat.model.Addb;

import java.io.Serializable;

import java.util.List;

import javax.inject.Named;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

@Named("addbBean")
@ViewScoped

public class AddbBean implements Serializable {

    @Getter
    @Setter
    private UploadedFile file;
    @Getter
    @Setter
    private Addb current;
    @Getter
    @Setter
    private String Address;
    @Getter
    @Setter
    private String email;

    @PostConstruct
    public void init() {

        current = new Addb();
        System.out.println("###########################       statt" + current.toString());
    }

}
