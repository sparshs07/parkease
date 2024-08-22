<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title># Booking Dashboard #</title>

    <link rel="stylesheet" href="static/css/common.css">
    <link rel="stylesheet" href="static/css/form.css">
    <link rel="stylesheet" href="static/css/form1.css">
</head>
<body>
    <div id="main_page_body">
        <%@ include file="header.jsp" %> 
        <iframe style="padding-left: 25%;" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3668.192244957851!2d79.9343101748549!3d23.16318261106712!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3981ae0c31e2bf2f%3A0x2e83bb9375080697!2sRussel%20Chowk%2C%20Napier%20Town%2C%20Jabalpur%2C%20Madhya%20Pradesh%20482001!5e0!3m2!1sen!2sin!4v1695211884956!5m2!1sen!2sin" width="800" height="220" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        <!-- <%@ include file="menu.jsp" %> -->
        <div id="maincontent" style="width: 100% ;display: flex;align-items: center;justify-content: center;">

       
        <div id="main_body "  style="width:40% ;background-color:rgb(213, 235, 251,0.7);margin-top:5rem;padding-bottom:5rem;margin-bottom:5rem;">
            <table border="0" width="100%">
                <tr>
                    <!-- <td width="30%">
                        
                        <br><br>
                        <h2><%= user.getUserName() %></h2>
                    </td> -->
                    <td width="70%">
                        <h2 style="align-self:center; padding-left: 4rem;"><%= user.getUserName().toUpperCase() %> BOOK YOUR SLOT</h2>

                        <!-- <a href="show.do">Show My Products</a><br><br> -->
                        <div id="setstyle" style="width:100% ; display:flex;flex-direction: column; align-items:center ;justify-self: center;">
                        <form action="book_slot.do" method="post">
                            Enter Car Number: <input type="text" name="car_number"><br>
                            Date: <input type="date" name="date"><br>
                            Time: <input type="time" name="time"><br>
                             <input type="submit" value="Book Slot">
                        </form>
                        </div>

                        <!-- <div style="border: 1 px solid red;" > -->
                            <!-- <form action="book_slot.do" method="post"  >
                                <table id="form_box">
                                    <tr>
                                        <th>
                                            <label for="unm">Enter Car Number:</label>
                                        </th>
                                        <td class="field_box">
                                            <input type="text" id="cnum" required name="car_number">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <label for="eml">Date :</label>
                                        </th>
                                        <td class="field_box">
                                            <input type="date" name="date">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <label for="pwd">Time:</label>
                                        </th>
                                        <td class="field_box">
                                            <input type="time" name="time">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" id="btn_box">
                                            <input type="submit" value="Book Slot">
                                        </td>
                                    </tr>
                                </table>
                            </form> -->
                        <!-- </div> -->
                    </td>
                </tr>
            </table>            
        </div> 
    </div>
        <%@ include file="footer.jsp" %> 
    </div>
</body>
</html>