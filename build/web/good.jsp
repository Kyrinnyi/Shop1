
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
                        <a class="menu" href="controler?auth=goods&group=g&page=1"> <fmt:message key="shop"/></a><br>
                        <a class="menu" href="controler?auth=busket"> <fmt:message key="busket"/></a><br>
                </td>
                <td width="92%"> 
                        <c:if test ="${admin}">
                            <form action ="controler" method = "post">
                                <input type="number" name="availible" value="${good.available}"/>
                                <input type="hidden" name="auth" value="set available"/>
                                <input type="submit" name="ok" value="<fmt:message key="setavailable"/>"/>
                                <input type="hidden" name="goodid" value="${good.id}"/>
                            </form>
                        </c:if>
                        <h2> <c:out value="${good.name}"/></h2>

                        <h3><fmt:message key="price"/>:  <c:out value="${good.price}"/></h3>
                        <p><fmt:message key="available"/>: <c:out value="${good.available}"/></p>

                        <img src="<%=request.getContextPath()%>/images/${good.picture}" alt="no picture" height="200">
                        <p><c:out value="${good.description}"/><br></p>   
           
                        <c:if test ="${!admin}">
                            <form action ="controler" method = "post">
                                <input type="number" name="amount" value="1"/>
                                <input type="submit" name="ok" value=" <fmt:message key="addtobusket"/>"/>
                                <input type="hidden" name="auth" value="busket"/>
                                <input type="hidden" name="goodid" value="${good.id}"/>
                            </form>
                        </c:if>
                </td>
               </tr>
              </table>
              <h2>&nbsp;</h2>
              </td>
          </tr>
        </table>
    </body>
</html>
