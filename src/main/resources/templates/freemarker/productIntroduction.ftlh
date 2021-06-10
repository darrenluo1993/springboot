<#ftl>
<html>
<head>
<title>Welcome!</title>
<style type="text/css">
.product-list {
    font-size: 14px;
    text-align: center;
    border-radius: 5px;
    border-collapse: collapse;
}

.product-list tr {
    height: 25px;
    line-height: 25px;
}

.product-list tr th {
    width: 150px;
    border: 1px solid #666666;
}

.product-list tr td {
    border: 1px solid #666666;
}
</style>
</head>
<body>
    <#if user == "Darren Luo">
        <h1>Welcome administrator ${user}!</h1>
    <#else>
        <h1>Welcome ${user}!</h1>
    </#if>
    <p>Our latest product: <a href="${latestProduct.url}">${latestProduct.name}</a>!</p>
    <#list productList>
        <p>Our product list:</p>
        <table class="product-list">
            <tr>
                <th>产品名称</th>
                <th>价格</th>
                <th>库存</th>
                <th>描述</th>
                <th>创建人</th>
                <th>创建时间</th>
            </tr>
            <#items as product>
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.description}</td>
                    <td>${product.createdBy}</td>
                    <td>${product.createdTime?datetime}</td>
                </tr>
            </#items>
        </table>
    </#list>
    <#-- <p>Home URL: <a href="${homeUrl}">${homeUrl}</a></p> -->
</body>
</html>