<%@ page import="models.CarDetail"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title># Booking Slot #</title>

    <link rel="stylesheet" href="static/css/common.css">
    <link rel="stylesheet" href="static/css/form1.css">

    <style>
        #price_table{
            background-color: aliceblue;
            border: 2px solid rgb(47, 126, 128);
            border-radius: 5px;
      
            padding: 5px;
        }

        
    </style>
</head>
<body>
    <div id="main_page_body">
        <%@ include file="header.jsp" %> 
        
        <div id="menu"></div> 
        <% CarDetail carDetail=(CarDetail)session.getAttribute("car_detail");%>
        <div id="main_body">

            <h1>Check Price</h1>
            <form action="finalpage.jsp" method="post">
                
                <div id="form_box">                    
                    <!-- <div>
                        <div class="label_box">
                            <label for="eml">Price :</label>
                        </div>
                        <div class="field_box">
                            <input type="text" class="field" required name="email" id="eml">
                            <br><small id="eml_err" class="err_msg">Enter valid email...</small>
                        </div>
                    </div>
                    <div>
                        <div class="label_box">
                            <label for="pwd">Password :</label>
                        </div>
                        <div class="field_box">
                            <input type="password" class="field" required name="password" id="pwd">
                            <br><small id="pwd_err" class="err_msg">Enter valid password...</small>
                        </div>
                    </div>   -->
                    <div style="padding-left: 70px;">
                        <table id="price_table" border="1">
                            <tr>
                                <th>DURATION</th>&nbsp;&nbsp;&nbsp;&nbsp;
                                <th>OUR PRICE</th>
                            </tr>
                            <tr>
                                <td>30 Min.</td>
                                <td>30 Rs.</td>
                            </tr>
                            <tr>
                                <td>45 Min.</td>
                                <td>45 Rs.</td>
                            </tr>
                            <tr>
                                <td>60 Min.</td>
                                <td>60 Rs.</td>
                            </tr>
                            <tr>
                                <td>75 Min.</td>
                                <td>75 Rs.</td>
                            </tr>
                            <tr>
                                <td>90 Min.</td>
                                <td>90 Rs.</td>
                            </tr>
                        </table>
                    </div>
                    
                    <div>
                        <input type="submit" id="btn" value="Confirm">
                    </div>                  
                </div>                
            </form>
        </div> 
        
        <%@ include file="footer.jsp" %> 
    </div>


    
</body>
</html>