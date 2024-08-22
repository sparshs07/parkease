package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import models.User;
import models.Product;
import models.SellerProduct;

@WebServlet("/add.do")
public class AddProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        if(user != null) {
            ArrayList<Product> products = Product.collectAllProducts();
            request.setAttribute("products", products);
            
            request.getRequestDispatcher("add_product.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        String forwardPage = "signin.jsp";

        if(user != null) {
            Integer productId = Integer.parseInt(request.getParameter("product"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            Integer price = Integer.parseInt(request.getParameter("price"));
            String productName = request.getParameter("product_name");
            Boolean isUpdate = Boolean.parseBoolean(request.getParameter("isupdate"));

            SellerProduct sellerProduct = new SellerProduct(productName, new Product(productId), user, quantity, price);
            
            String productFolderPath = getServletContext().getRealPath("/WEB-INF/uploads/"+user.getEmail()+"/products");
            
            if(isUpdate) {
                Integer sellerProductId = Integer.parseInt(request.getParameter("seller_product_id"));
                sellerProduct.setSellerProductId(sellerProductId);
                
                sellerProduct.updateProduct();

                String oldFolderName = request.getParameter("seller_product_name");
                
                String newProductFolder = productName.replace(' ', '_')+"_"+sellerProduct.getSellerProductId();
                String oldProductFolder = oldFolderName.replace(' ', '_')+"_"+sellerProduct.getSellerProductId();

                File oldFile = new File(productFolderPath, oldProductFolder);
                File newFile = new File(productFolderPath, newProductFolder);
                boolean huaKya = oldFile.renameTo(newFile);

                System.out.println(huaKya);
                System.out.println(oldFile);
                System.out.println(newFile);

                forwardPage = "show.do";
            } else {
                sellerProduct.saveProduct();
                
                String productFolder = productName.replace(' ', '_')+"_"+sellerProduct.getSellerProductId();
                File file = new File(productFolderPath, productFolder);
                file.mkdir();

                ArrayList<Product> products = Product.collectAllProducts();
                request.setAttribute("products", products);
                forwardPage = "add_product.jsp";
            }
        } 
            
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }
}
