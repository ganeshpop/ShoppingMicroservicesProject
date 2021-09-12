<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <title>Sign Up</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Sans:400,600" rel="stylesheet">
    <link rel="stylesheet" href='<c:url value="dist/css/signupStyle.css"/>'>
    <script src="https://unpkg.com/animejs@3.0.1/lib/anime.min.js"></script>
    <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>
<body class="is-boxed has-animations">
<div class="body-wrap">
    <header class="site-header">
        <div class="container">
            <div class="site-header-inner">
                <div class="brand header-brand">
                    <h1 class="m-0">
                        <a href="#">
                            <img class="header-logo-image" src="dist/images/logo.svg" alt="Logo">
                        </a>
                    </h1>
                </div>
            </div>
        </div>
    </header>

    <main>
        <section class="hero">
            <div class="container">
                <div class="hero-inner">
                    <div class="hero-copy">
                        <h1 class="hero-title mt-0">Lets Get You Signed Up</h1>
                        <p class="hero-paragraph">Fill the small form to get started </p>

                        <div class="hero-cta"><a class="button button-primary" href="./">Go Back</a>
                        </div>
                    </div>
                    <div class="pricing-table">
                        <div class="pricing-table-inner is-revealing">
                            <div class="pricing-table-main">
                                <p>Enter The Following Details</p>
                                <spring:form action="./createUser" method="post" modelAttribute="signup">
                                    <ul class="pricing-table-features list-reset text-xs">
                                        <li>
                                            <span>  <label
                                                    style="width: 186px; display: inline-block">Name : </label> <spring:input
                                                    path="userName"
                                                    cssClass="text simpleTextField"
                                                    maxlength="200"
                                                    cssStyle="width:50%;"/></span>
                                        </li>
                                        <spring:errors path="userName" cssClass="error"/>
                                        <li>
                                            <span>  <label
                                                    style="width: 186px; display: inline-block">Address : </label> <spring:input
                                                    path="address"
                                                    cssClass="text simpleTextField"
                                                    maxlength="200"
                                                    cssStyle="width:50%;"/></span>
                                        </li>
                                        <spring:errors path="address" cssClass="error"/>

                                        <li>
                                            <span><label
                                                    style="width: 186px; display: inline-block"> Password : </label> <spring:input
                                                    type="password" path="passwordOne"
                                                    maxlength="200"
                                                    cssStyle="width:50%;"/></span>
                                        </li>
                                        <spring:errors path="passwordOne" cssClass="error"/>
                                        <li>
                                            <span><label
                                                    style="width: 190px; display: inline-block">Conform Password : </label><spring:input
                                                    type="password" path="passwordTwo"
                                                    maxlength="200" cssStyle="width:50%;"/></span>
                                        </li>
                                        <spring:errors path="passwordTwo" cssClass="error"/>

                                    </ul>
                                    <input class="button button-primary button-shadow button-block" type="submit"
                                           value="Sign Up">
                                </spring:form>
                                <div class="pricing-table-cta mb-8">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer class="site-footer">
        <div class="container">
            <div class="site-footer-inner">
                <div class="brand footer-brand">
                    <a href="./">
                        <img class="header-logo-image" src="dist/images/logo.svg" alt="Logo">
                    </a>
                </div>
                <ul class="footer-links list-reset">
                    <li>
                        <a href="mailto: ganeshgo1999@gmail.com">Contact</a>
                    </li>
                    <li>
                        <a href="https://www.linkedin.com/in/s-sai-ganesh-koundinya-gollapudi-25285118a/">About Me</a>
                    </li>
                    <li>
                        <a href="sessionSupport">Support</a>
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
</body>
</html>
