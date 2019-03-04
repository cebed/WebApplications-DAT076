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
 * @author Rick
 */
@Entity
@Table(name = "RATINGDB")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Ratingdb.findAll", query = "SELECT r FROM Ratingdb a")
    , @NamedQuery(name = "Ratingdb.findById", query = "SELECT r FROM Ratingdb r WHERE r.id = :id")
    , @NamedQuery(name = "Rating.findByRating", query = "SELECT r FROM Ratingdb r WHERE r.rating = :rating")
    , @NamedQuery(name = "Addb.findByComment", query = "SELECT r FROM Ratingdb r WHERE r.comment = :comment")
    , @NamedQuery(name = "Addb.findByRater", query = "SELECT r FROM Ratingdb r WHERE r.rater = :rater")})
public class Ratingdb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID")
    private String id;
    @Size(max = 100)
    @Column(name = "RATING")
    private Integer rating;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private String comment;
    @Size(max = 100)
    @Column(name = "RATER")
    private String rater;

    @Size(max = 100)
    
    public Ratingdb(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
    

}
