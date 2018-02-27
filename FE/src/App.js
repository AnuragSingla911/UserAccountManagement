import React, { Component } from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import Login from './login';

import 'react-tabs/style/react-tabs.css';

class App extends Component {

constructor(props){
super(props);
this.state ={
  dataSource : [],
isLoading : 0,
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

console.log("request")
   return fetch('http://localhost:8080/admin',{
    credentials: 'include',

   }
   )
    .then((Response) => {
      console.log(Response.status);
      if(Response.status === 200){
         return Response.json();
      }else{
        return null;
      }
      
    }).then((responseJson) => {
      if(responseJson != null){
         this.setState({
             isLoading: 1,
             dataSource:responseJson,
           });
      }else{
        this.setState({
          isLoading : 2,
        })
      }
    })
    .catch((e) => {
console.log("error");
       
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
       credentials: 'include',

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
 if(this.state.isLoading === 2){
    return ( <Login updateState ={this.onSelectTab} />);
  }else
	if(this.state.isLoading === 1){
    return (
        <Tabs forceRenderTabPanel defaultIndex={0}>
              <TabList>
                       <Tab>Admin</Tab>
              </TabList>

            <TabPanel>
                  <div>
                 
                     {this.state.dataSource.map(function(player,i) {
                     return <h2 key={i}>{player.fullName}</h2>
                })}
                     
                  </div>

           </TabPanel>
           
          </Tabs>
    );
  }

return (       <h2>loading</h2>

);
}
}



export default App;
