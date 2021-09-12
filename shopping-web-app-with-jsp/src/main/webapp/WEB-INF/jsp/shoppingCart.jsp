<%--suppress HtmlUnknownTag --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sprng" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href='<c:url value="dist/css/cartPage.css"/>'>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>City Mart</title>
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


        <main style="margin-bottom: 300px">
            <div class="container-sm">
                <div class="pricing-inner section-inner">
                    <div class="pricing-header text-center">
                        <h2 class="section-title mt-0">Cart Summary</h2>
                    </div>

                    <div class="summary-tables-wrap">
                        <div class="summary-table">
                            <div class="pricing-table-inner is-revealing">
                                <div class="pricing-table-main">

                                    <div class="pricing-table-header pb-24">
                                        <div class="pricing-table-price"><span
                                                class="pricing-table-price-currency h2">$</span><span
                                                class="pricing-table-price-amount h1">${cartFare}</span><span
                                                class="text-xs">&nbsp;(cart total)</span></div>
                                    </div>
                                    <ul class="pricing-table-features list-reset text-xs">
                                        <li>
                                                <span> Number Of Items In Cart :<dataTag
                                                        style="color: white; margin: 0; padding: 0;"> ${itemsInCart}&nbsp;</dataTag> </span>
                                        </li>
                                    </ul>

                                </div>
                                <a class="button button-primary button-shadow button-block summary-button"
                                   href="orderNow">Add More Items</a>
                                <c:if test="${not empty cart.cartItems}">

                                <a class="button button-primary button-shadow button-block summary-button"
                                   href="checkOut">Check Out</a>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h3 class="section-title mt-0" style="text-align: center; margin-top: 24px">Cart Items</h3>
            <c:if test="${empty cart.cartItems}">
            <h3 class="section-title mt-0" style="text-align: center; margin-top: 24px;font-size: 20px">Your Cart is Empty</h3>
            </c:if>
            <section class="products-display">
                <c:forEach items="${products}" var="product">
                    <section class="pricing section">
                        <div class="container-sm">
                            <div class="pricing-inner section-inner">
                                <div class="pricing-tables-wrap">
                                    <div class="pricing-table">
                                        <div class="pricing-table-inner is-revealing">
                                            <div class="pricing-table-main">
                                                <div class="pricing-table-header pb-24">

                                                    <sprng:form action="addToCart?code=${product.code}">
                                                    <div>
                                                        <c:forEach items="${cart.cartItems}" var="item">
                                                            <c:if test="${product.code == item.key}">
                                                                <c:set var="requestedQuantity" value="${item.value} "/>
                                                                <c:set var="productPrice" value="${product.price} "/>
                                                                <c:set var="productFare"
                                                                       value="${requestedQuantity * productPrice} "/>
                                                            </c:if>
                                                        </c:forEach>
                                                        <ul class="pricing-table-features list-reset text-xs">
                                                            <li>
                                                                Product Code:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    &nbsp; ${product.code}</dataTag>
                                                            </li>
                                                            <li>
                                                                Name:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    &nbsp; ${product.name}</dataTag>
                                                            </li>
                                                            <li>
                                                                Description:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    &nbsp; ${product.description}</dataTag>
                                                            </li>
                                                            <li>
                                                                Unit Price:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    &nbsp; $ ${productPrice}</dataTag>
                                                            </li>
                                                            <li>
                                                                Requested Quantity:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    <c:forEach items="${cart.cartItems}" var="item">
                                                                    <c:if test="${product.code == item.key}">
                                                                    &nbsp; ${item.value}</dataTag>
                                                                </c:if>
                                                                </c:forEach>
                                                            </li>
                                                            <li>
                                                                Available Quantity:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    <c:forEach items="${inventories}" var="inventory">
                                                                    <c:if test="${product.code == inventory.productCode}">
                                                                    &nbsp; ${inventory.availableQuantity}</dataTag>
                                                                </c:if>
                                                                </c:forEach>
                                                            </li>
                                                            <li>
                                                                Total Price:
                                                                <dataTag
                                                                        style="color: white; margin: 0; padding: 0;">
                                                                    &nbsp; $ <fmt:formatNumber type = "number"
                                                                                               maxFractionDigits = "2" value = "${productFare}" /></dataTag>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="increaseQuantity">

                                                    <input class="button button-primary button-shadow button-block"
                                                           type="submit"
                                                           value="+1 Quantity">
                                                </div>

                                                </sprng:form>

                                                <sprng:form action="removeFromCart?code=${product.code}">
                                                <div class="reduceQuantity">
                                                    <input class="button button-primary button-shadow button-block"
                                                           type="submit"
                                                           value="-1 Quantity">
                                                    </sprng:form>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </section>

                </c:forEach>

            </section>
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







