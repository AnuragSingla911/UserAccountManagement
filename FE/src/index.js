import React from 'react';
import {render} from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {Router, Route} from 'react-router';
import { browserHistory } from 'react-router';
import Register from './register';


render(

	 <Router history={browserHistory}>
        <Route path="/" component={App}/>
                <Route path="/register" component={Register}/>


    </Router>,
	 document.getElementById('root')
	);

registerServiceWorker();
