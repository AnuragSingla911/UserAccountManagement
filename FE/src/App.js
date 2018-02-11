import React, { Component } from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';

import 'react-tabs/style/react-tabs.css';

class App extends Component {

constructor(){
super();
this.state ={
  dataSource : [],
isLoading : true,
username : '',
fullname : '',
password : ''
};
this.getAccounts();
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleChangeFullName = this.handleChangeFullName.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);

    this.onSelectTab = this.onSelectTab.bind(this);
    this.register = this.register.bind(this);

}

onSelectTab(){

this.getAccounts();

}



getAccounts() {

   return fetch('http://localhost:8080/admin'
   )
    .then((Response) => Response.json())
    .then((responseJson) => {
      console.log("response");
       this.setState({
             isLoading: false,
             dataSource: responseJson,
           });
    })
    

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
console.log(this.state.username);
console.log(this.state.fullname);
console.log(this.state.password);
 return fetch('http://localhost:8080/register', {
   method: 'post',
   headers: {'Content-Type':'application/json'},
   body: JSON.stringify({
    "userID" : this.state.username,
    "password": this.state.password,
    "fullName" : this.state.fullname
   })
  }
   )
    .then((responseJson) => {
      this.getAccounts();
    })


   


}

  render() {
          console.log('start'+ this.state.isLoading);

	if(this.state.isLoading === false){
    return (
        <Tabs forceRenderTabPanel defaultIndex={0}>
              <TabList>
                       <Tab>Admin</Tab>
                       <Tab>User</Tab>
              </TabList>

            <TabPanel>
                  <div>
                 
                     {this.state.dataSource.map(function(player,i) {
                     return <h2 key={i}>{player.fullName}</h2>
                })}
                     
                  </div>

           </TabPanel>
            <TabPanel>
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
             </TabPanel>
          </Tabs>
    );
  }

return (       <h2>loading</h2>

);
}
}



export default App;
