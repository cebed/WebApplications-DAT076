
package com.Hemlagat.model.session;

import com.Hemlagat.model.Addb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nurabd
 */
@Named(value = "shoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {

    @Getter
    @Setter
    private Addb item;
    
    /**
     * Creates a new instance of ShoppingCart
     */
    public ShoppingCart() {
    }
    
}
