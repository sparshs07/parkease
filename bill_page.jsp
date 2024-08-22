<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title># FinalPage #</title>

    <link rel="stylesheet" href="static/css/common.css">
    <link rel="stylesheet" href="static/css/form1.css">
</head>
<body>
    <div id="main_page_body">
        <%@ include file="header.jsp" %> 
        
        <div id="menu"></div> 
        
        <div id="main_body">
            <form action="bill_page.jsp" method="post">
                

                <div id="form_box">                    
                    <div>
                      
                    </div>
                    <div>
                        <div class="label_box">
                            <label for="pwd"><h3>Scan QR to Pay!!</h3></label>
                            
                        </div>

                       
                        <!-- <div class="field_box">
                            <input type="password" class="field" required name="password" id="pwd">
                            <br><small id="pwd_err" class="err_msg">Enter valid password...</small>
                        </div> -->
                    </div>  
                    
                    <!-- <div>
                        <input type="submit" id="btn" value="Show Bill">
                    </div>                   -->
                </div> 
                              
            </form>
            <div style="padding-left: 10px; padding-top: 30px;"><img src="static/images/QRCode.png" width="300px" alt=""></div>

            <!-- <div style="color: black;">
                <label for="pwd">Thanks for using ParkEase</label>
            </div>  -->
        </div> 
        
        <%@ include file="footer.jsp" %> 
    </div>


    
</body>
</html>