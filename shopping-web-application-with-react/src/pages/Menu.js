import logo from '../dist/images/logo.svg'
import React, {useState} from 'react';
import {Redirect, Link} from 'react-router-dom';
import NavBar from "../layout/NavBar"
import "../shoppingLoginStyle.css"

function Menu(props) {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const login = () => {
        if (userName === "ravi" && password === "password") {
            return <Redirect to='/menu'/>
        } else {
            console.log("Failed");
        }
    }
    return (
        <div>
            <NavBar/>
            <div className="is-boxed has-animations">
                <div className="body-wrap,site-header">
                    <main>
                        <section className="pricing section">
                            <div className="container-sm">
                                <div className="pricing-inner section-inner">
                                    <div className="pricing-header text-center">
                                        <h2 className="section-title mt-0">Welcome To City Mart User {userName}</h2>
                                        <p className="section-paragraph mb-0" style={{color: 'azure'}}>You Are Logged
                                            In</p>
                                    </div>
                                        <div className="pricing-tables-wrap">
                                            <div className="pricing-table">
                                                <div className="pricing-table-inner is-revealing">
                                                    <div className="pricing-table-main">
                                                        <div className="pricing-table-header pb-24">
                                                            <div className="pricing-table-price"><span
                                                                className="pricing-table-price-amount h2">Previous Order Details</span>
                                                            </div>
                                                            <div className="pricing-table-price"
                                                                 style={{paddingTop: '10px'}}><span
                                                                className="pricing-table-price-currency h4">$</span><span
                                                                className="pricing-table-price-amount h3">{2+2}</span><span
                                                                className="text-xs">&nbsp;(Total Fare)</span></div>
                                                        </div>
                                                        <ul className="pricing-table-features list-reset text-xs">
                                                            <li>
                                                                <span> Number Of Items In Purchased :<datatag style={{
                                                                    color: 'white',
                                                                    margin: 0,
                                                                    padding: 0
                                                                }}> {'lastOrder.itemCount'}&nbsp;</datatag> </span>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div className="pricing-table-cta mb-8">
                                                        <a className="button button-primary button-shadow button-block"
                                                          >View Details</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
                            <div className="pricing-tables-wrap">
                                <div className="pricing-table">
                                    <div className="pricing-table-inner is-revealing">
                                        <div className="pricing-table-main">
                                            <div className="pricing-table-header pb-24">
                                                <div className="pricing-table-price">
                                                    <h5>You Haven't Ordered Anything Yet</h5>
                                                </div>
                                            </div>
                                            <div className="pricing-table-cta mb-8">
                                                <a className="button button-primary button-shadow button-block"
                                                   href="orderNow">Order Now</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </main>
                </div>
                <footer className="site-footer">
                    <div className="container">
                        <div className="site-footer-inner">
                            <div className="brand footer-brand">
                                <Link to="/menu">
                                    <img className="header-logo-image" src={logo} alt="Logo"/>
                                </Link>
                            </div>
                            <ul className="footer-links list-reset">
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
                            <ul className="footer-social-links list-reset">
                                <li>
                                    <a href="#">
                                        <span className="screen-reader-text">Facebook</span>
                                        <svg width={16} height={16} xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M6.023 16L6 9H3V6h3V4c0-2.7 1.672-4 4.08-4 1.153 0 2.144.086 2.433.124v2.821h-1.67c-1.31 0-1.563.623-1.563 1.536V6H13l-1 3H9.28v7H6.023z"
                                                fill="#0270D7"/>
                                        </svg>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span className="screen-reader-text">Twitter</span>
                                        <svg width={16} height={16} xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M16 3c-.6.3-1.2.4-1.9.5.7-.4 1.2-1 1.4-1.8-.6.4-1.3.6-2.1.8-.6-.6-1.5-1-2.4-1-1.7 0-3.2 1.5-3.2 3.3 0 .3 0 .5.1.7-2.7-.1-5.2-1.4-6.8-3.4-.3.5-.4 1-.4 1.7 0 1.1.6 2.1 1.5 2.7-.5 0-1-.2-1.5-.4C.7 7.7 1.8 9 3.3 9.3c-.3.1-.6.1-.9.1-.2 0-.4 0-.6-.1.4 1.3 1.6 2.3 3.1 2.3-1.1.9-2.5 1.4-4.1 1.4H0c1.5.9 3.2 1.5 5 1.5 6 0 9.3-5 9.3-9.3v-.4C15 4.3 15.6 3.7 16 3z"
                                                fill="#0270D7"/>
                                        </svg>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span className="screen-reader-text">Google</span>
                                        <svg width={16} height={16} xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M7.9 7v2.4H12c-.2 1-1.2 3-4 3-2.4 0-4.3-2-4.3-4.4 0-2.4 2-4.4 4.3-4.4 1.4 0 2.3.6 2.8 1.1l1.9-1.8C11.5 1.7 9.9 1 8 1 4.1 1 1 4.1 1 8s3.1 7 7 7c4 0 6.7-2.8 6.7-6.8 0-.5 0-.8-.1-1.2H7.9z"
                                                fill="#0270D7"/>
                                        </svg>
                                    </a>
                                </li>
                            </ul>
                            <div className="footer-copyright"> @Spring Boot</div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>


    );
}

export default Menu;