import React, { Component } from 'react';
import App from './App.js'

import { browserHistory } from 'react-router';


class Login extends Component {

constructor(props){
super(props);
this.state ={
user : '',
password : ''
};
   this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.login = this.login.bind(this);
    this.openRegister = this.openRegister.bind(this);
}


handleChangeUserName(event) {
    this.setState( { user : event.target.value});
  }

  

  handleChangePassword(event) {
    this.setState( { password : event.target.value});
  }

login(event){
event.preventDefault();
console.log(this.state.user);
console.log(this.state.password);
const data = new FormData(event.target);
 return fetch('http://localhost:8080/signin', {
   method: 'post',
   body: data,
   credentials: 'include',
  }
   )
    .then((responseJson) => {
    	console.log("responseJson");
    	this.props.updateState();
    }).catch((e)=>{
    	    	this.props.updateState();

    	console.log("error in login"+e)
    })


   


}

openRegister(){
	console.log("register button clicked")
  browserHistory.push('/register');

}

render() {

return( <div>
                 
                     
                     <form onSubmit={this.login}>
  <label>
    UserName:
    <input type="text" name="username" onChange={this.handleChangeUserName}/>
  </label>
<div></div>
  
<div></div>
 <label>
    Password:
    <input type="text" name="password" onChange={this.handleChangePassword}/>
  </label>
  <div></div>
  <input type="submit" value="Submit" />
</form>

<button onClick={this.openRegister}>
Register   </button>                  
                  </div>
                  );
}

}

export default Login;