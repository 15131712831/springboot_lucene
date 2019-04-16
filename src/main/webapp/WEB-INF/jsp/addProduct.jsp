<%@page pageEncoding="UTF-8" %>
<html>
<head >

    <meta http-equiv="content-type" content="text/html;charset=UTF-8" >

</head>
<body>


<h1>添加商品</h1>
<form action="${pageContext.request.contextPath}/product/addProduct" method="POST" enctype="multipart/form-data">
    商品名称:<input type="text" name="name"><br/>
    商品价格:<input type="text" name="price"><br/>
    商品描述:<input type="text" name="content"><br/>
    商品图片:<input type="file" name="img"><br/>
    商品状态:<input type="text" name="status"><br/>
    商品上产日期:<input type="date" name="reg_date"><br/>
    商品产地:<input type="text" name="address"><br/>
    <input type="submit" value="提交"><br/>
</form>

</body>
</html>
