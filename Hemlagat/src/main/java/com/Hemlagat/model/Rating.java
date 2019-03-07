/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.UserTransaction;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rick
 */
@Entity
@Table(name = "RATING")
@XmlRootElement
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String chef;
    private int rate;
    private String comment;
    private String rater;

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRater() {
        return rater;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chef != null ? chef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.chef == null && other.chef != null) || (this.chef != null && !this.chef.equals(other.chef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hemlagat.model.Rating[ id=" + chef + " ]";
    }

    
    
}
