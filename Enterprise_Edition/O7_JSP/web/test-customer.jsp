<%@ page import="java.util.ArrayList" %>
<%@ page import="lk.ijse.jsp.dto.CustomerDTO" %><%--
  Created by IntelliJ IDEA.
  User: sanu
  Date: 2023-07-24
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
<%


    ArrayList<CustomerDTO> allCustomers = new ArrayList();
    allCustomers.add(new CustomerDTO("C001", "Iman", "India"));
    allCustomers.add(new CustomerDTO("C002", "Achintha", "Sri Lanka"));
    allCustomers.add(new CustomerDTO("C003", "Oshanda", "Somalia"));
    allCustomers.add(new CustomerDTO("C004", "Ushan", "Ithiyopia"));

%>


<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th>Customer ID</th>
        <th>Customer Name</th>
        <th>Customer Address</th>
    </tr>
    </thead>
    <tbody>
    <%for (CustomerDTO customer : allCustomers) { %>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getAddress()%></td>
    </tr>
    <%}%>
    </tbody>
</table>

</body>
</html>
