<%@ page import="java.util.ArrayList,models.SellerProductImage" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: Product Image Upload Page ::</title>

    <link rel="stylesheet" href="static/css/common.css">
    <link rel="stylesheet" href="static/css/form1.css">

    <style>
        .field {
            background-color: #fff;
        }

        #form_box {
            width: 45%;
        }

        #upload_heading {
            color: #fff;
        }
    </style>
</head>
<body>
    <div id="main_page_body">
        <%@ include file="header.jsp" %> 
        
        <%@ include file="menu.jsp" %>
        
        <div id="main_body">
            
            <form action="product_pic_upload.do" method="post" enctype="multipart/form-data">
                
                <input type="hidden" name="seller_product_name" value='<%= request.getParameter("seller_product_name") %>'>
                <input type="hidden" name="seller_product_id" value='<%= request.getParameter("seller_product_id") %>'>
               
               
                <div id="form_box">
                    <h1><%= request.getAttribute("seller_product_name") %></h1>
                    <h3 id="upload_heading">Upload Product Images</h3>                    
                    <div>
                        <div class="label_box">
                            <label for="p_img">Choose Product Pics :</label>
                        </div>
                        <div class="field_box">
                            <input type="file" class="field" multiple required name="product_image" id="p_img">
                            <br><small id="eml_err" class="err_msg">Enter valid email...</small>
                        </div>
                    </div>  
                    <div>
                        <input type="submit" id="btn" value="Upload">
                    </div>                  
                </div>                
            </form>

            <div style="margin-top: 50px;">
                <% ArrayList<SellerProductImage> sellerProductImage = (ArrayList<SellerProductImage>)request.getAttribute("seller_product_images"); %>
            
                <% for(SellerProductImage image : sellerProductImage) { %>
                    <img src="product_image.do?seller_product_image_id=<%= image.getSellerProductImageId() %>&image_path=<%= image.getImagePath() %>" height="200" style="margin: 10px">
                <% } %>   
            </div>
        </div> 
        
        <%@ include file="footer.jsp" %> 
    </div>
</body>
</html>