/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nurabd
 */
@Entity
@Table(name = "ADDB")
@XmlRootElement
@RequiredArgsConstructor
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
    , @NamedQuery(name = "Addb.findByOther2", query = "SELECT a FROM Addb a WHERE a.other2 = :other2")
    , @NamedQuery(name = "Addb.findByPhoto", query = "SELECT a FROM Addb a WHERE a.photo = :photo")
  

})
public class Addb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "ID")
    @Getter
    @Setter
    private String id;
    @Size(max = 100)
    @Column(name = "TITLE")
    @Getter
    @Setter
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    @Getter
    @Setter
    private Double price;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;
    @Column(name = "QUANTITY")
    @Getter
    @Setter
    private Integer quantity;
    @Column(name = "WEIGHT")
    @Getter
    @Setter
    private Double weight;
    @Column(name = "VEGETARIAN")
    @Getter
    @Setter
    private Boolean vegetarian;
    @Column(name = "NUTS")
    @Getter
    @Setter
    private Boolean nuts;
    @Column(name = "LACTOSE")
    @Getter
    @Setter
    private Boolean lactose;
    @Column(name = "GLUTEN")
    @Getter
    @Setter
    private Boolean gluten;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    @Getter
    @Setter
    private String address;
    @Size(max = 100)
    @Column(name = "OTHER")
    @Getter
    @Setter
    private String other;// fungerar som tillfällig stad -- ta inte bort Nur
    @Size(max = 100)
    @Column(name = "OTHER2")
    @Getter
    @Setter
    private String other2; // fungerar som tillfällig post no -- ta inte bort Nur
    
    @Getter
    @Setter
    @Column(name = "STATUS")
    private String status; 
    @Lob
    @Column(name = "PHOTO")
    @Getter
    @Setter
    private byte[] photo;
   @Getter
    @Setter
    @JoinColumn(name = "USERID", referencedColumnName = "EMAIL")
    @ManyToOne
    private Userdb userid;
    
    
    
    private static final Logger LOG = Logger.getLogger(Addb.class.getName());
private static final AtomicLong counter = new AtomicLong(100);

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
