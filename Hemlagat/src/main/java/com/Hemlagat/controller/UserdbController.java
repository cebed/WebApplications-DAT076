package com.Hemlagat.controller;

import com.Hemlagat.model.Userdb;
import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.controller.util.PaginationHelper;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.session.UserBean;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

@Named("userdbController")
@SessionScoped
public class UserdbController implements Serializable {

    private Userdb current;
    private DataModel items = null;

    @Getter
    @Setter
    private String confirmPassword;
    @Getter
    @Setter
    private String password;

    @Inject
    private UserBean userBean;

    @EJB
    private com.Hemlagat.model.UserdbFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UserdbController() {
    }

    public Userdb getSelected() {
        if (current == null) {
            current = new Userdb();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserdbFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Userdb) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Userdb();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserdbCreated"));
            return "/Locationpage.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("User already exist");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Userdb) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
<<<<<<< Updated upstream
=======
            //current.setUsername(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
            //System.out.println(current);
            //System.out.println(current.getPassword());
            //System.out.println(confirmPassword);
 
>>>>>>> Stashed changes
            final Userdb userdb = getFacade().find(userBean.getEmail());
            userdb.setPassword(password);
            getFacade().edit(userdb);
  
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserdbUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Userdb) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserdbDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Userdb getUserdb(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Userdb.class)
    public static class UserdbControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserdbController controller = (UserdbController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userdbController");
            return controller.getUserdb(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Userdb) {
                Userdb o = (Userdb) object;
                return getStringKey(o.getEmail());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Userdb.class.getName());
            }
        }

    }

}
