/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.AddbFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Daniel Cebe
 */
@Named
@RequestScoped
public class ImageStoreController {

    private UploadedFile file;

    @EJB
    private AddbFacade add;

    public void storeImage() {
        final Addb addb = new Addb();
        addb.setPhoto(file.getContents());
        //if vi ändrar till int 
        addb.setId("9");

        add.create(addb);

    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
