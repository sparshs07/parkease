package models;

import java.sql.*;
import java.util.*;

public class SellerProductImage {
    //Fields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private Integer sellerProductImageId;
    private SellerProduct sellerProduct;
    private String imagePath;

    //Constructors +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SellerProductImage(Integer sellerProductImageId, String imagePath) {
        this.sellerProductImageId = sellerProductImageId;
        this.imagePath = imagePath;
    }

    public SellerProductImage() {
    
    }

    //Other Methods ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static ArrayList<SellerProductImage> collectProductImages(Integer sellerProductId) {
        ArrayList<SellerProductImage> sellerProductImages = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "select seller_product_image_id,image_path from seller_product_images where seller_product_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sellerProductId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                sellerProductImages.add(new SellerProductImage(rs.getInt(1), rs.getString(2))); 
            }   

        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sellerProductImages;
    }
    
    

    public static void saveImages(ArrayList<String> imagePaths, Integer sellerProductId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecartdb?user=root&password=1234");

            String query = "insert into seller_product_images (seller_product_id,image_path) value (?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sellerProductId);

            for(String imagePath : imagePaths) {
                ps.setString(2, imagePath);
                ps.executeUpdate();
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Getter-Setters +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Integer getSellerProductImageId() {
        return sellerProductImageId;
    }
    public void setSellerProductImageId(Integer sellerProductImageId) {
        this.sellerProductImageId = sellerProductImageId;
    }
    public SellerProduct getSellerProduct() {
        return sellerProduct;
    }
    public void setSellerProduct(SellerProduct sellerProduct) {
        this.sellerProduct = sellerProduct;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}