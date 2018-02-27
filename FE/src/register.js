import React, { Component } from 'react';
import App from './App.js'



class Register extends Component {

constructor(props){
super(props);
this.state ={
username : '',
fullname : '',
password : ''
};
   this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleChangeFullName = this.handleChangeFullName.bind(this);

    this.register = this.register.bind(this);
}


handleChangeUserName(event) {
    this.setState( { username : event.target.value});
  }

handleChangeFullName(event) {
    this.setState( { fullname : event.target.value});
  }
  

  handleChangePassword(event) {
    this.setState( { password : event.target.value});
  }

register(event){
event.preventDefault();
console.log('username' + this.state.username);
console.log(this.state.fullname);
console.log(this.state.password);
 return fetch('http://localhost:8080/register', {
   method: 'post',
   headers: {'Content-Type':'application/json'},
       credentials: 'include',
         'Access-Control-Allow-Origin':'*',


   body: JSON.stringify({
    "userID" : this.state.username,
    "password": this.state.password,
    "fullName" : this.state.fullname
   })
  }
   )
    .then((responseJson) => {
    }).catch((e) => {
      console.log(e);
    })


   


}
render() {

return( <div>
                 
                     
                     <form onSubmit={this.register}>
  <label>
    UserName:
    <input type="text" name="username" onChange={this.handleChangeUserName}/>
  </label>
<div></div>
   <label>
    FullName:
    <input type="text" name="name" onChange={this.handleChangeFullName}/>
  </label>
<div></div>
 <label>
    Password:
    <input type="text" name="password" onChange={this.handleChangePassword}/>
  </label>
  <div></div>
  <input type="submit" value="Submit" />
</form>
                     
                  </div>
                  );
}

}

export default Register;