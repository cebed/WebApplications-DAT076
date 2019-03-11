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
@Table(name = "USERDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdb.findAll", query = "SELECT u FROM Userdb u")
    , @NamedQuery(name = "Userdb.findByEmail", query = "SELECT u FROM Userdb u WHERE u.email = :email")
    , @NamedQuery(name = "Userdb.findByUsername", query = "SELECT u FROM Userdb u WHERE u.username = :username")
    , @NamedQuery(name = "Userdb.findByPassword", query = "SELECT u FROM Userdb u WHERE u.password = :password")})
public class Userdb implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;

    public Userdb() {
    }

    public Userdb(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdb)) {
            return false;
        }
        Userdb other = (Userdb) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Hemlagat.model.Userdb[ email=" + email + " ]";
    }

}
