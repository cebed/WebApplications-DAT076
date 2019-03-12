/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model.session;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author Daniel Cebe
 */

@Data
@Named
@SessionScoped
public class UserBean implements Serializable {
    
    private String email;
    private String username;
    
    
    public boolean isLoggedIn(){
        return username!=null;
        
    }
    
    
}
