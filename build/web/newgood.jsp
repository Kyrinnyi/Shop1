
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
        <table border="0" cellpadding="0" cellspacing="0" class="tbl1" width="1000">
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
                    <h2><fmt:message key="addtitle"/></h2>
                        <form action ="controler" method ="post">
                            <table>
                                <tr>
                                    <td><fmt:message key="nameofg"/>:</td> 
                                    <td><input type="text" name="name" value="" required="true"/></td> 
                                </tr>
                                <tr>
                                    <td><fmt:message key="nameofgr"/>:</td> 
                                    <td><input type="text" name="namer" value="" required="true"/></td> 
                                </tr> 
                                <tr>
                                    <td><fmt:message key="description"/>:</td> 
                                    <td><textarea rows="3" cols="50" name="description" required="true"></TEXTAREA></td> 
                                </tr>  
                                <tr>
                                    <td><fmt:message key="descriptionr"/>:</td> 
                                    <td><textarea rows="3" cols="50" name="descriptionr" required="true"></TEXTAREA></td> 
                                </tr>  
                                <tr>
                                    <td><fmt:message key="price"/>:</td> 
                                    <td><input type="number" name="price" value="" required="true" step="0.01"/></td> 
                                </tr>  
                                <tr>
                                    <td> <fmt:message key="picture"/>:</td> 
                                    <td><input type="text" name="picture" value="" required="true"/></td> 
                                </tr>
                                <tr>
                                    <td><fmt:message key="available"/>:</td> 
                                    <td><input type="number" name="availible" value="" required="true"/></td> 
                                </tr>  
                                <tr>
                                    <td><fmt:message key="group"/>:</td> 
                                    <td><input type="text" name="group" value="" required="true"/></td> 
                                </tr>  
                                <tr>
                                    <td> <fmt:message key="groupr"/>:</td> 
                                    <td><input type="text" name="groupr" value="" required="true"/></td> 
                                </tr>  
                            </table>
                            <input type="submit" name="ok" value="<fmt:message key="addgood"/>"/>
                            <input type="hidden" name="auth" value="add good"/>
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



