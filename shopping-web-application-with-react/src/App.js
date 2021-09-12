import Welcome from "./pages/Welcome"
import SignUp from "./pages/SignUp"
import Menu from "./pages/Menu"
import {Route, Switch} from "react-router-dom";
import Login from "./pages/Login";


function App() {

    return (
        <div>
            <Switch>
                <Route path="/" exact>
                    <Welcome/>
                </Route>
                <Route path="/login">
                    <Login/>
                </Route>
                <Route path="/signup">
                    <SignUp/>
                </Route>
                <Route path="/menu">
                <Menu/>
            </Route>
            </Switch>
        </div>
    )
        ;
}

export default App;