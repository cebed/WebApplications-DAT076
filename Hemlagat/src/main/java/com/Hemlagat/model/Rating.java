
package com.Hemlagat.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "RATING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
    , @NamedQuery(name = "Rating.findByChef", query = "SELECT r FROM Rating r WHERE r.chef = :chef")
    , @NamedQuery(name = "Rating.findByComment", query = "SELECT r FROM Rating r WHERE r.comment = :comment")
    , @NamedQuery(name = "Rating.findByRate", query = "SELECT r FROM Rating r WHERE r.rate = :rate")
    , @NamedQuery(name = "Rating.findByRater", query = "SELECT r FROM Rating r WHERE r.rater = :rater")})
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Automatic generated value
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CHEF")
    private String chef;
    @Size(max = 255)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "RATE")
    private Integer rate;
    @Size(max = 255)
    @Column(name = "RATER")
    private String rater;

    public Rating() {
    }

    public Rating(String chef) {
        this.chef = chef;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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
        return "com.Hemlagat.model.Rating[ chef=" + chef + " ]";
    }
    
}
