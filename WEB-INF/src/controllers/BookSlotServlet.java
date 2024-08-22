package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.util.*;

import models.User;
import models.CarDetail;

@WebServlet("/book_slot.do")

public class BookSlotServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null){
            String carNumber=request.getParameter("car_number");
            String date=request.getParameter("date");
            String time=request.getParameter("time");

            CarDetail cd=new CarDetail(carNumber,date,time);

            if(cd.putCarDetails(user.getUserId())){
                session.setAttribute("car_detail",cd);
        request.getRequestDispatcher("book_slot.jsp").forward(request,response);
            }
           
        }
        request.getRequestDispatcher("signin.jsp").forward(request,response);
    }
}