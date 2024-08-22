package listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import models.Product;

import java.util.ArrayList;

public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ArrayList<Product> products = Product.collectAllProducts();

        
    }

    public void contextDestroyed(ServletContextEvent event) {
        
    }
}
