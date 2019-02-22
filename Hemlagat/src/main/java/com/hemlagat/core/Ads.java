/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hemlagat.core;

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
 * @author nurabd
 */
@Entity
@Table(name = "ADS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ads.findAll", query = "SELECT a FROM Ads a")
    , @NamedQuery(name = "Ads.findById", query = "SELECT a FROM Ads a WHERE a.id = :id")
    , @NamedQuery(name = "Ads.findByDescription", query = "SELECT a FROM Ads a WHERE a.description = :description")
    , @NamedQuery(name = "Ads.findByPrice", query = "SELECT a FROM Ads a WHERE a.price = :price")
    , @NamedQuery(name = "Ads.findByAddress", query = "SELECT a FROM Ads a WHERE a.address = :address")
    , @NamedQuery(name = "Ads.findByQuantity", query = "SELECT a FROM Ads a WHERE a.quantity = :quantity")
    , @NamedQuery(name = "Ads.findByWeight", query = "SELECT a FROM Ads a WHERE a.weight = :weight")
    , @NamedQuery(name = "Ads.findByTitle", query = "SELECT a FROM Ads a WHERE a.title = :title")})
public class Ads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCRIPTION")
    private Character description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "WEIGHT")
    private Double weight;
    @Column(name = "TITLE")
    private Character title;

    public Ads() {
    }

    public Ads(String id) {
        this.id = id;
    }

    public Ads(String id, Character description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character getDescription() {
        return description;
    }

    public void setDescription(Character description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Character getTitle() {
        return title;
    }

    public void setTitle(Character title) {
        this.title = title;
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
        if (!(object instanceof Ads)) {
            return false;
        }
        Ads other = (Ads) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hemlagat.core.Ads[ id=" + id + " ]";
    }
    
}
