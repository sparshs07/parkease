package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SellerProduct {
    private Integer sellerProductId;
    private String name;
    private Product product;
    private User user;
    private Integer quantity;
    private Integer price;

    public SellerProduct() {
        
    }

    public SellerProduct(Integer sellerProductId) {
        this.sellerProductId = sellerProductId;
    }

    public SellerProduct(Integer sellerProductId, Product product, User user, Integer quantity, Integer price, String name) {
        this.sellerProductId = sellerProductId;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public SellerProduct(String name, Product product, User user, Integer quantity, Integer price) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public void updateProduct() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "update seller_products set product_id=?,quantity=?,price=?,name=? where seller_product_id=? and user_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, product.getProductId());
            ps.setInt(2, quantity);
            ps.setInt(3, price);
            ps.setString(4, name);

            ps.setInt(5, sellerProductId);
            ps.setInt(6, user.getUserId());

            int rowCount = ps.executeUpdate();


            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }   
    }

    public void getProductDetail() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "select * from seller_products where seller_product_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sellerProductId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                product = new Product(rs.getInt("product_id"));
                quantity = rs.getInt("quantity");
                price = rs.getInt("price");
                name = rs.getString("name");
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteSellerProduct(Integer sellerProductId, Integer userId) {
        boolean flag = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "delete from seller_products where seller_product_id=? and user_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, sellerProductId);
            ps.setInt(2, userId);

            int val = ps.executeUpdate();

            if(val == 1) {
                flag = true;
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static ArrayList<SellerProduct> collectSellerProducts(User user) {
        ArrayList<SellerProduct> sellerProducts = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");
        
            String query = "select sp.seller_product_id,p.product_id,p.name,sp.quantity,sp.price,sp.name " + 
            "from seller_products as sp inner join products as p " + 
            "where sp.product_id=p.product_id and user_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, user.getUserId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                sellerProducts.add(new SellerProduct(rs.getInt(1), new Product(rs.getInt(2), rs.getString(3)), user, rs.getInt(4), rs.getInt(5), rs.getString(6)));     
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sellerProducts;
    }

    public void saveProduct() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "insert into seller_products (product_id,user_id,quantity,price,name) value (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, product.getProductId());
            ps.setInt(2, user.getUserId());
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, name);


            int rowCount = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()) {
                sellerProductId = rs.getInt(1);
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }   
    }

    public Integer getSellerProductId() {
        return sellerProductId;
    }

    public void setSellerProductId(Integer sellerProductId) {
        this.sellerProductId = sellerProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
