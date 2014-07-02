
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
                     <form action ="controler" method = "post" >
                         <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="confirm"/>"/>
                            <input type="hidden" name="com" value="confirm"/>
                            <input type="hidden" name="auth" value="order status"/>
                            <input type="hidden" name="order" value="${order.id}"/>
                        </form>
                        <form action ="controler" method = "post">
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="setpaid"/>"/>
                            <input type="hidden" name="com" value="set paid"/>
                            <input type="hidden" name="auth" value="order status"/>
                            <input type="hidden" name="order" value="${order.id}"/>
                        </form>
                        <form action ="controler" method = "post">
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="close"/>"/>
                            <input type="hidden" name="com" value="close"/>
                            <input type="hidden" name="auth" value="order status"/>
                            <input type="hidden" name="order" value="${order.id}"/>
                        </form>
                        <form action ="controler" method = "post">        
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="setclientblack"/>"/>
                            <input type="hidden" name="auth" value="set client black"/>
                            <input type="hidden" name="client" value="${client.id}"/>
                        </form> <br> 
                        <form action ="controler" method ="post">
                            <input type="hidden" name="login" value="${adm.login}"/>
                            <input type="hidden" name="pass" value="${adm.pass}"/>
                            <input type="hidden" name="auth" value="auth"/>
                            <input type="submit" style="width:130px;" name="ok" value="<fmt:message key="orders"/>"/>
                        </form>
                </td>
                <td width="92%"> 
                    <h2><fmt:message key="order"/></h2>
                    
                    <table>
                        <tr>
                            <td><fmt:message key="Orderid"/>:</td> 
                            <td><c:out value="${order.id}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="Isconfirmed"/>:</td> 
                            <td><c:out value="${order.confirmed}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="Ispaid"/>:</td> 
                            <td><c:out value="${order.paid}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="Isclosed"/>:</td> 
                            <td><c:out value="${order.closed}"/></td> 
                        </tr>
                    </table>
                    
                    <h3><fmt:message key="client"/></h3>
                    
                    <table>
                        <tr>
                            <td><fmt:message key="id"/>:</td> 
                            <td><c:out value="${client.id}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="name"/>:</td> 
                            <td><c:out value="${client.name}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="surename"/>:</td> 
                            <td><c:out value="${client.surename}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="phone"/>:</td> 
                            <td><c:out value="${client.phone}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="mail"/>:</td> 
                            <td><c:out value="${client.mail}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="addres"/>:</td> 
                            <td><c:out value="${client.addres}"/></td> 
                        </tr>
                        <tr>
                            <td><fmt:message key="isBlack"/>: </td> 
                            <td><span class = "colortext"><c:out value="${client.black}"/> </span></td> 
                        </tr>
                    </table>
                    <br>
      
                         <table border = "1">
                                    <th>   
                                     <fmt:message key="id"/>
                                    <td> <fmt:message key="nameofg"/> </td>
                                    <td> <fmt:message key="amount"/></td>
                                    <td> <fmt:message key="available"/></td>
                                    <td> <fmt:message key="price"/></td>
                                </th>
                                <c:set var="sum" value="${0}"/>
     
                               <c:forEach var="good" items='<%=session.getAttribute("listgoods")%>'>
                                    <tr>
                                        <td> <c:out value="${good.good.id}"/> </td> 
                                        <td> <c:out value="${good.good.name}"/> </td> 
                                        <td> <c:out value="${good.amount}"/> </td> 
                                        <td> <c:out value="${good.good.available}"/> </td> 
                                        <td> <c:out value="${good.good.price}"/> </td> 
                                        <td><form action ="controler" method = "get">
                                            <input type="hidden" name="auth" value="good"/>
                                            <input type="hidden" name="goodid" value="${good.good.id}"/>
                                            <input type="submit" name="ok" value="<fmt:message key="show"/>"/>
                                            </form>
                                        </td>
                                    </tr>
                                    <c:set var="sum" value="${sum + good.good.price * good.amount}"/>
                              </c:forEach>
                            </table>

                        <h3><fmt:message key="sum"/>: <mytag:round number ="${sum}"/></h3>
               </td>
               </tr>
              </table>
              <h2>&nbsp;</h2>
              </td>
          </tr>
        </table>
    </body>
</html>
