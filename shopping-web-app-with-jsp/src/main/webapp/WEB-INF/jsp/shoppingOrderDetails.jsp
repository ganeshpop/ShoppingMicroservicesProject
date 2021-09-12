<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>Swipe Out Status</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href='<c:url value="dist/css/orderDetailsStyle.css"/>'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Details</title>
    <link rel="stylesheet" href='<c:url value="vendor/bootstrap/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="vendor/animate/animate.css"/>'>
    <link rel="stylesheet" href='<c:url value="vendor/select2/select2.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="vendor/perfect-scrollbar/perfect-scrollbar.css"/>'>
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Sans:400,600" rel="stylesheet">
    <script src="https://unpkg.com/animejs@3.0.1/lib/anime.min.js"></script>
    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>
<body class="is-boxed has-animations">

<nav class="navMenu" style="padding-top: 20px">
    <ul class="menuItems">
        <li class="menuLi" style="padding-inline: 20px "><a class="menuA" href='menu'
                                                            data-item='Home'>Home</a></li>
        <li class="menuLi" style="padding-inline: 20px "><a class="menuA" href='getOrders'
                                                            data-item='Order History'>Order History</a></li>
        <li class="menuLi" style="padding-inline: 20px"><a class="menuA" href='orderNow' data-item='Order Now'>Order
            Now</a></li>
        <li class="menuLi" style="padding-inline: 20px"><a class="menuA" href='showCart' data-item='Cart'>Cart</a></li>
        <li class="menuLi" style="padding-inline: 20px"><a class="menuA" href='passwordChange'
                                                           data-item='Change Password'>Change Password</a></li>
        <li class="menuLi" style="padding-inline: 20px"><a class="menuA" href='logout' data-item='Log Out'>Log Out</a>
        </li>
    </ul>
</nav>

<div class="is-boxed has-animations">
    <div class="body-wrap,site-header">


        <main>

            <div class="container-sm">
                <div class="pricing-inner section-inner">
                    <div class="pricing-header text-center">
                        <h2 class="section-title mt-0">Order Details</h2>
                    </div>

                    <div class="pricing-tables-wrap">
                        <div class="pricing-table">
                            <div class="pricing-table-inner is-revealing">
                                <div class="pricing-table-main">

                                    <div class="pricing-table-header pb-24">
                                        <h4 class="section-title mt-0" style="text-align: center">${message}</h4>
<%--                                        <h4 class="section-title mt-0" style="text-align: center">Order Details</h4>--%>
                                        <div class="pricing-table-price"><span
                                                class="pricing-table-price-currency h2">$</span><span
                                                class="pricing-table-price-amount h1"><fmt:formatNumber type = "number"
                                                                                                        maxFractionDigits = "2" value = "${order.totalFare}" /></span><span
                                                class="text-xs">&nbsp;(total fare)</span></div>
                                    </div>
                                    <ul class="pricing-table-features list-reset text-xs">
                                        <li>
                                                <span>Order ID: <dataTag
                                                        style="color: white; margin: 0; padding: 0;"> ${order.id }</dataTag></span>
                                        </li>
                                        <li>
                                                <span>Address :<dataTag
                                                        style="color: white; margin: 0; padding: 0;"> ${user.address}</dataTag> </span>
                                        </li>
                                        <li>
                                                <span> Number Of Items Purchased :<dataTag
                                                        style="color: white; margin: 0; padding: 0;"> ${order.itemCount}&nbsp;</dataTag> </span>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3 class="section-title mt-0" style="text-align: center">Items Purchased</h3>
            <c:if test="${not empty order.items}">
            <div class="limiter">
                <div class="container-table100">
                    <div class="wrap-table100">
                        <div class="table100 ver3 m-b-110">
                            <div class="table100-head">
                                <table>
                                    <thead>
                                    <tr class="row100 head">
                                        <th class="cell100 column1">Product ID</th>
                                        <th class="cell100 column2">Unit Price</th>
                                        <th class="cell100 column3">Name</th>
                                        <th class="cell100 column4">Quantity</th>
                                        <th class="cell100 column5">Total Price</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                            <div class="table100-body js-pscroll">
                                <table>
                                    <tbody>
                                    <c:forEach items="${products}" var="product">
                                        <c:forEach items="${order.items}" var="item">
                                            <c:if test="${item.productCode == product.code}">
                                                <c:set var="quantity" value="${item.quantity}"/>
                                                <c:set var="totalPrice" value="${item.productPrice}"/>
                                            </c:if>
                                        </c:forEach>
                                        <tr class="row100 body">
                                            <td class="cell100 column1">${product.id}</td>
                                            <td class="cell100 column2">$${product.price}</td>
                                            <td class="cell100 column3">${product.name}</td>
                                            <td class="cell100 column4">${quantity}</td>
                                            <td class="cell100 column4">$${totalPrice}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>


                        </div>
                        </c:if>
                        <c:if test="${empty order.items}">
                            <h2 style="text-align: center">No Items Found</h2>
                        </c:if>


                        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>


                        <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
                        <script>
                            $('.js-pscroll').each(function () {
                                let ps = new PerfectScrollbar(this);
                                $(window).on('resize', function () {
                                    ps.update();
                                })
                            });
                        </script>

                    </div>
                </div>
            </div>
        </main>
        <footer class="site-footer">
            <div class="container">
                <div class="site-footer-inner">
                    <div class="brand footer-brand">
                        <a href="menu">
                            <img class="header-logo-image" src="dist/images/logo.svg" alt="Logo">
                        </a>
                    </div>
                    <ul class="footer-links list-reset">
                        <li>
                            <a href="mailto: ganeshgo1999@gmail.com">Contact</a>
                        </li>
                        <li>
                            <a href="https://www.linkedin.com/in/s-sai-ganesh-koundinya-gollapudi-25285118a/">About
                                Me</a>
                        </li>
                        <li>
                            <a href="support">Support</a>
                        </li>
                    </ul>
                    <ul class="footer-social-links list-reset">
                        <li>
                            <a href="#">
                                <span class="screen-reader-text">Facebook</span>
                                <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                            d="M6.023 16L6 9H3V6h3V4c0-2.7 1.672-4 4.08-4 1.153 0 2.144.086 2.433.124v2.821h-1.67c-1.31 0-1.563.623-1.563 1.536V6H13l-1 3H9.28v7H6.023z"
                                            fill="#0270D7"></path>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="screen-reader-text">Twitter</span>
                                <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                            d="M16 3c-.6.3-1.2.4-1.9.5.7-.4 1.2-1 1.4-1.8-.6.4-1.3.6-2.1.8-.6-.6-1.5-1-2.4-1-1.7 0-3.2 1.5-3.2 3.3 0 .3 0 .5.1.7-2.7-.1-5.2-1.4-6.8-3.4-.3.5-.4 1-.4 1.7 0 1.1.6 2.1 1.5 2.7-.5 0-1-.2-1.5-.4C.7 7.7 1.8 9 3.3 9.3c-.3.1-.6.1-.9.1-.2 0-.4 0-.6-.1.4 1.3 1.6 2.3 3.1 2.3-1.1.9-2.5 1.4-4.1 1.4H0c1.5.9 3.2 1.5 5 1.5 6 0 9.3-5 9.3-9.3v-.4C15 4.3 15.6 3.7 16 3z"
                                            fill="#0270D7"></path>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="screen-reader-text">Google</span>
                                <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                            d="M7.9 7v2.4H12c-.2 1-1.2 3-4 3-2.4 0-4.3-2-4.3-4.4 0-2.4 2-4.4 4.3-4.4 1.4 0 2.3.6 2.8 1.1l1.9-1.8C11.5 1.7 9.9 1 8 1 4.1 1 1 4.1 1 8s3.1 7 7 7c4 0 6.7-2.8 6.7-6.8 0-.5 0-.8-.1-1.2H7.9z"
                                            fill="#0270D7"></path>
                                </svg>
                            </a>
                        </li>
                    </ul>
                    <div class="footer-copyright"> @Spring Boot</div>
                </div>
            </div>
        </footer>
    </div>
    <script src="dist/js/main.min.js"></script>
</div>
</body>
</html>

