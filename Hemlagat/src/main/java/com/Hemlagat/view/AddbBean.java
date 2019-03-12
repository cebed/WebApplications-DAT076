package com.Hemlagat.view;

import com.Hemlagat.controller.*;
import com.Hemlagat.model.Addb;
import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.controller.util.PaginationHelper;
import com.Hemlagat.model.AddbFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

@Named("addbBean")
@ViewScoped
public class AddbBean implements Serializable {

    private UploadedFile file;
    private Addb current;
    @Getter
    @Setter
    private String Address;
   
    private List<Addb> item = null;
    
   
    
  

    @EJB
    private com.Hemlagat.model.AddbFacade ejbFacade;
   
    
    //private  Addb add;
    
    @PostConstruct
    private void init(){
        current = new Addb();
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Addb getSelected() {
        if (current == null) {
            current = new Addb();
            

        }

        return current;
    }

    public void setSelected(Addb current) {
        this.current = current;
    }

    private AddbFacade getFacade() {
        return ejbFacade;
    }

    
    public String prepareList() {
        recreateModel();
        return "List";
    }

   

    public String prepareCreate() {
        current = new Addb();
       
        return "Create";
    }

    /**
    public void storeImage() {
        final Addb addb = new Addb();
        addb.setPhoto(file.getContents());
        //if vi ändrar till int 
        addb.setId("9");

        add.create(addb);
    }
*/
    
    public String create() {
        try {
            current.setPhoto(file.getContents());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AddbCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

   /*        använd den här för att uppdatera om varan är såld eller ej*/

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AddbUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        
   
    }

   

   

    
    
    
    
    /* om varan är såld använd den här*/
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AddbDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

   

   
   

   

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Addb getAddb(java.lang.String id) {
        return ejbFacade.find(id);
    }

 
    //////////////////////////////NUr//////////////////////////////

    public List<Addb> getItem() {

        if (item == null) {
            item = ejbFacade.findByAdress(Address);
        }
        return item;
    }

    public String getIte() {

        
        item = ejbFacade.findByAdress(Address);
        System.out.println("####### jag är fär");
        return "/addb/List.xhtml";
    }

    
    
    
   
    
    
    
    
    
    
    

    private void recreateModel() {
        item = null;
    }
    
     
    
    /**
     * ALTER TABLE ADDB
ADD FOREIGN KEY (USERID)
REFERENCES USERDB(EMAIL);
     */
}
