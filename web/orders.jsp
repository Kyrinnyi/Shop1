
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jstl/fmt"%> 
<fmt:setBundle basename="ua.unif.resources.res"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>   
        <style>
            .colortext {
                color: red;
            }    
        </style>
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
                   <td width="8%" valign="top"><img src="single_pixel.gif" width="140" height="1"><br><br><br><br>
                        <form action ="controler" method = "post">
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="newgood"/>"/>
                            <input type="hidden" name="auth" value="new good"/>
                        </form>
                        <form action ="controler" method = "post">
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="shop"/>"/>
                            <input type="hidden" name="auth" value="goods"/>
                            <input type="hidden" name="group" value="g"/>
                            <input type="hidden" name="page" value="1"/>
                        </form>
                </td>
                <td width="92%"> 
                    <h2><fmt:message key="orders"/></h2>
                     <table border = "1">
                        <th>   
                            <fmt:message key="id" /> 
                            <td> <fmt:message key="conf"/> </td>
                            <td> <fmt:message key="paid"/> </td>             
                        </th>
                        <c:forEach var="order" items='<%=session.getAttribute("orderlist")%>'>
                            <tr>

                                <td> <c:out value="${order.id}"/> </td> 
                                <td> <c:out value="${order.confirmed}"/> </td> 
                                <td> <c:out value="${order.paid}"/> </td> 
                                <td><form action ="controler" method = "post">
                                    <input type="submit" name="ok" value="<fmt:message key="show" />"/>
                                    <input type="hidden" name="auth" value="orders"/>
                                    <input type="hidden" name="order" value="${order.id}"/>
                                </form></td> 
                            </tr>
                        </c:forEach> 
                    </table>
               </td>
               </tr>
              </table>
              <h2>&nbsp;</h2>
              </td>
          </tr>
        </table>
    </body>
</html>
