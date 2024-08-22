package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

import models.User;

@WebServlet("/product_image.do")
public class CollectProductImagesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        if(user != null) {
            String imagePath = request.getParameter("image_path");

            InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/"+user.getEmail()+"/products/"+imagePath);
         
            OutputStream os = response.getOutputStream();

            int count = 0;
            byte[] arr = new byte[256];

            while((count=is.read(arr)) != -1) {
                os.write(arr);
            }

            os.flush();
            os.close();
        }
    }
}