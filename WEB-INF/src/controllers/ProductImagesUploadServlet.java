package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.util.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import models.User;
import models.SellerProductImage;

@WebServlet("/product_pic_upload.do")
public class ProductImagesUploadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer sellerProductId = Integer.parseInt(request.getParameter("seller_product_id"));
        
        ArrayList<SellerProductImage> sellerProductImages = SellerProductImage.collectProductImages(sellerProductId);
        request.setAttribute("seller_product_images", sellerProductImages);

        request.setAttribute("seller_product_name", request.getParameter("seller_product_name"));

        request.getRequestDispatcher("product_pic_upload.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        
        String forwardPage = "signin.jsp";

        if(user != null) {
            if(ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
                    ArrayList<String> imagePaths = new ArrayList<>();
                    
                    String uploadFolderName = "";
                    String uploadPath = null;
                    int sellerProductId = 0;
                    
                    for(FileItem fileItem : fileItems) {
                        if(fileItem.isFormField()) {
                            String fieldName = fileItem.getFieldName();
                            if(fieldName.equals("seller_product_name")) {
                                request.setAttribute("seller_product_name", fileItem.getString());
                                uploadFolderName = fileItem.getString().replace(' ', '_')+"_";
                                // System.out.println(uploadFolderName+" ^^^^^^^1^^^^^^^^");
                            } else {
                                sellerProductId = Integer.parseInt(fileItem.getString());
                                uploadFolderName = uploadFolderName + fileItem.getString();
                                uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/"+user.getEmail()+"/products/"+uploadFolderName);
                                System.out.println(uploadPath+" ^^^^^^^^##^^^^^^^");
                            }
                        } else {
                            // System.out.println(uploadFolderName+" ^^^^^^^^3^^^^^^^");
                            String filePathWithExt = fileItem.getName();
                            
                            imagePaths.add(uploadFolderName+"/"+filePathWithExt);
                            File uploadImagePath = new File(uploadPath, filePathWithExt);

                            try {
                                fileItem.write(uploadImagePath);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    System.out.println(imagePaths);
                    SellerProductImage.saveImages(imagePaths, sellerProductId);

                    ArrayList<SellerProductImage> sellerProductImages = SellerProductImage.collectProductImages(sellerProductId);
                    request.setAttribute("seller_product_images", sellerProductImages);

                    forwardPage = "product_pic_upload.jsp";
                } catch(FileUploadException e) {
                    e.printStackTrace();
                }
            }   
        } 
        
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }
}
