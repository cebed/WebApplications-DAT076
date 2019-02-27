/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Cebe
 */
@Entity
@Table(name = "ADDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Addb.findAll", query = "SELECT a FROM Addb a")
    , @NamedQuery(name = "Addb.findById", query = "SELECT a FROM Addb a WHERE a.id = :id")
    , @NamedQuery(name = "Addb.findByTitle", query = "SELECT a FROM Addb a WHERE a.title = :title")
    , @NamedQuery(name = "Addb.findByPrice", query = "SELECT a FROM Addb a WHERE a.price = :price")
    , @NamedQuery(name = "Addb.findByDescription", query = "SELECT a FROM Addb a WHERE a.description = :description")
    , @NamedQuery(name = "Addb.findByQuantity", query = "SELECT a FROM Addb a WHERE a.quantity = :quantity")
    , @NamedQuery(name = "Addb.findByWeight", query = "SELECT a FROM Addb a WHERE a.weight = :weight")
    , @NamedQuery(name = "Addb.findByVegetarian", query = "SELECT a FROM Addb a WHERE a.vegetarian = :vegetarian")
    , @NamedQuery(name = "Addb.findByNuts", query = "SELECT a FROM Addb a WHERE a.nuts = :nuts")
    , @NamedQuery(name = "Addb.findByLactose", query = "SELECT a FROM Addb a WHERE a.lactose = :lactose")
    , @NamedQuery(name = "Addb.findByGluten", query = "SELECT a FROM Addb a WHERE a.gluten = :gluten")
    , @NamedQuery(name = "Addb.findByAddress", query = "SELECT a FROM Addb a WHERE a.address = :address")
    , @NamedQuery(name = "Addb.findByOther", query = "SELECT a FROM Addb a WHERE a.other = :other")
    , @NamedQuery(name = "Addb.findByOther2", query = "SELECT a FROM Addb a WHERE a.other2 = :other2")})
public class Addb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID")
    private String id;
    @Size(max = 100)
    @Column(name = "TITLE")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "WEIGHT")
    private Double weight;
    @Column(name = "VEGETARIAN")
    private Boolean vegetarian;
    @Column(name = "NUTS")
    private Boolean nuts;
    @Column(name = "LACTOSE")
    private Boolean lactose;
    @Column(name = "GLUTEN")
    private Boolean gluten;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 100)
    @Column(name = "OTHER")
    private String other;
    @Size(max = 100)
    @Column(name = "OTHER2")
    private String other2;

    public Addb() {
    }

    public Addb(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getNuts() {
        return nuts;
    }

    public void setNuts(Boolean nuts) {
        this.nuts = nuts;
    }

    public Boolean getLactose() {
        return lactose;
    }

    public void setLactose(Boolean lactose) {
        this.lactose = lactose;
    }

    public Boolean getGluten() {
        return gluten;
    }

    public void setGluten(Boolean gluten) {
        this.gluten = gluten;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addb)) {
            return false;
        }
        Addb other = (Addb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hemlagat.model.Addb[ id=" + id + " ]";
    }
    
}
