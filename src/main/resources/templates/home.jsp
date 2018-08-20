<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "<a class="vglnk" href="http://www.w3.org/TR/html4/loose.dtd" rel="nofollow"><span>http</span><span>://</span><span>www</span><span>.</span><span>w3</span><span>.</span><span>org</span><span>/</span><span>TR</span><span>/</span><span>html4</span><span>/</span><span>loose</span><span>.</span><span>dtd</span></a>">
<%@ taglib uri="<a class="vglnk" href="http://java.sun.com/jsp/jstl/core" rel="nofollow"><span>http</span><span>://</span><span>java</span><span>.</span><span>sun</span><span>.</span><span>com</span><span>/</span><span>jsp</span><span>/</span><span>jstl</span><span>/</span><span>core</span></a>" prefix="c"%>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign UP Screen</title>
</head>
<body>
    <div align="center">
        <h1>Employee List</h1>
        <h3>
            <a href="newUser">New User</a>
        </h3>
        <table border="1">
 
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Password</th>
            <th>ConfirmPassword</th>
            <th>Country</th>
            <c:forEach var="user" items="${listUser}">
                <tr>
 
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.mobile}</td>
                    <td>${user.password}</td>
                    <td>${user.password}</td>
                    <td>${user.confirmpassword}</td>
                    <td>${user.country}</td>
                    <td><a href="editUser?id=${user.id}">Edit</a>
                             <a
                        href="deleteUser?id=${user.id}">Delete</a></td>
 
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>>