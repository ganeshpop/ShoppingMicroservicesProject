import './navBar.css';

function navBar() {
    return (
        <nav className="navMenu" style={{paddingTop: '20px'}}>
            <ul className="menuItems">
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Home">Home</a></li>
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Order History">Order History</a>
                </li>
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Order Now">Order
                    Now</a></li>
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Cart">Cart</a></li>
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Change Password">Change
                    Password</a>
                </li>
                <li className="menuLi" style={{paddingInline: '20px'}}><a className="menuA"
                                                                          data-item="Log Out">Log
                    Out</a>
                </li>
            </ul>
        </nav>

    );
}

export default navBar;