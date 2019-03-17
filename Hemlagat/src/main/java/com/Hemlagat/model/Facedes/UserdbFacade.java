
package com.Hemlagat.model.Facedes;

import com.Hemlagat.model.QUserdb;
import com.Hemlagat.model.Userdb;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel Cebe
 */
@Stateless
public class UserdbFacade extends AbstractFacade<Userdb> {

    @PersistenceContext(unitName = "com.mycompany_Hemlagat_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private JPAQuery query;
    private JPAQueryFactory qf;
    

    @PostConstruct
    public void setup() {
        query = new JPAQuery(em);
        qf = new JPAQueryFactory(em);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserdbFacade() {
        super(Userdb.class);
    }

    public Userdb findUser(String email, String password) {
       
        QUserdb tableofuser = QUserdb.userdb;
        System.out.println(query.from(tableofuser).fetch());
        Userdb result = qf.selectFrom(tableofuser).where(tableofuser.email.eq(email)).fetchOne();
        System.out.println(qf.selectFrom(tableofuser).where(tableofuser.email.eq(email)).fetch());
        if (result != null) {
            System.out.println("Found user");
            System.out.println("Password: >" + result.getPassword() + "<");
        }
        if (result != null && result.getPassword().equals(password)) {
            System.out.println("Found user and right password");
            return result;
        } else {
            System.out.println("Did not find user");
            return null;
        }
    }

}
