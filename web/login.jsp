<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jstl/fmt"%> 
<fmt:setBundle basename="ua.unif.resources.res"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>   
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" class="tbl1" width="800">
          <tr> 
            <td colspan="4"></td>
          </tr>
          <tr valign="top"> 
            <td width="60%" height="91"><img src="single_pixel.gif" width="1" height="140"></td>
            <td width="40%" height="91" colspan="3" valign="top" align="center"> 

            </td>
          </tr>
          <tr> 
            <td colspan="3"  > 
          <tr> 
            <td valign="top" colspan="4"> 
              <table width="90%" border="0" cellspacing="25">
               <tr>
                <td width="8%" valign="top"><img src="single_pixel.gif" width="140" height="1"><br>
                        
                </td>
                <td width="92%"> 
                     <form action ="controler" method ="post">
                        <fmt:message key="login"/>: <input type="text" name="login" value=""/>
                        <fmt:message key="password"/>: <input type="password" name="pass" value=""/>
                        <input type="hidden" name="auth" value="auth"/>
                        <input type="submit" name="ok" value="<fmt:message key="open"/>"/>
                    </form>
                </td>
               </tr>
              </table>
              <h2>&nbsp;</h2>
              </td>
          </tr>
        </table>
    </body>
</html>
