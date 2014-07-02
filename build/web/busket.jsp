<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag"%>
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
                        <a class="menu" href="controler?auth=goods&group=g&page=1"> <fmt:message key="shop"/></a><br>
                        <a class="menu" href="controler?auth=busket"> <fmt:message key="busket"/></a><br>
                </td>
                <td width="92%"> 
                    <table border="1px">
                            <th><fmt:message key="nameofg"/>
                                <td> <fmt:message key="price"/> </td>
                                <td> <fmt:message key="amount"/></td>
                            </th>
                            <c:set var="sum" value="${0}"/>
                            <c:forEach var="og" items='<%=session.getAttribute("busket")%>'>
                                <tr>
                                    <td> <c:out value="${og.good.name}"/> </td> 
                                    <td> <c:out value="${og.good.price}"/> </td> 
                                    <td> <c:out value="${og.amount}"/> </td> 
                                    <td><form action ="controler" method = "post">
                                        <input type="submit" name="ok" value="<fmt:message key="delete"/>"/>
                                        <input type="hidden" name="auth" value="delete"/>
                                        <input type="hidden" name="idog" value="${og.idog}"/>
                                    </form></td> 
                                </tr>
                                
                                <c:set var="sum" value="${sum + og.good.price * og.amount}"/>
                            </c:forEach> 
                        </table>
                                
                                <h3> <mytag:round number ="${sum}"/> </h3>  
                        <form action ="controler" method = "post">
                            <fmt:message key="name"/>: <input type="text" name="name" value="" required="true"/><br>
                            <fmt:message key="surename"/>: <input type="text" name="surename" value="" required="true"/><br>
                            <fmt:message key="mail"/>: <input type="email" name="mail" value="" required="true"/><br>
                            <fmt:message key="phone"/>: <input type="tel" name="phone" value="" required="true"/><br>
                            <fmt:message key="addres"/>: <input type="text" name="addres" value="" required="true"/><br>
                            <input type="submit" name="ok" value="<fmt:message key="confirmorder"/>"/>   
                            <input type="hidden" name="auth" value="confirm order"/> 
                        </form><br>
                        <a href="controler?auth=goods&group=g&page=1"> <fmt:message key="Continueshoping"/> </a>
                </td>
               </tr>
              </table>
              <h2>&nbsp;</h2>
              </td>
          </tr>
        </table>
    </body>
</html>









