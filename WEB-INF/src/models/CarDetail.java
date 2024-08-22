package models;

import java.io.*;
import java.util.*;
import java.sql.*;

public class CarDetail{
    private Integer carDetailId;
    private User user;
    private String carNumber;
    private String date;
    private String time;

    public CarDetail(String carNumber,String date,String time){
        this.carNumber=carNumber;
        this.date=date;
        this.time=time;
    }

    public  boolean putCarDetails(Integer userId){
        boolean flag=false;
         try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkeasedb?user=root&password=1234");
            String query = "insert into car_details (user_id, car_number, date, time) value ( ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setString(2, carNumber);
            ps.setString(3, date);
            ps.setString(4, time);
            

            int res = ps.executeUpdate();
            
            if(res == 1) {
                flag = true;
            }

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    
   


    public void setCarDetailId(Integer carDetailId){
        this.carDetailId=carDetailId;
    }
    public Integer getCarDetailId(){
        return carDetailId;
    }

    public void setCarNumber(String carNumber){
        this.carNumber=carNumber;
    }
    public String getCarNumber(){
        return carNumber;
    }

     public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return date;
    }

     public void setTime(String time){
        this.time=time;
    }
    public String getTime(){
        return time;
    }

     public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
}