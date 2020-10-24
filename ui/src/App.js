import React from 'react';

import './App.css';
import Home from './components/Home';
import UserProfile from './components/UserProfile';
import Poem from './components/Poem';
import AddPoem from './components/AddPoem';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Provider } from 'react-redux';
import { HashRouter } from 'react-router-dom';
import Header from './components/Header';
import store from './store';
import 'semantic-ui-css/semantic.min.css';
import './css/styles.css';
import { NotificationContainer } from 'react-notifications';
import 'react-notifications/lib/notifications.css';

class App extends React.Component {
	render() {
		const { history } = this.props;
		return (
			<Provider store={store}>
				<HashRouter>
					<div class="parent">
						<Header />

						<div id="wrapper" style={{ overflowY: 'auto' }}>
							<Route exact path="/" component={Home} />
							<Route exact path="/Home" component={Home} />
							<Route exact path="/AddPoem" component={AddPoem} />
							<Route exact path="/UserProfile" component={UserProfile} />
							<Route exact path="/Poem/:id" component={Poem} />
						</div>
						<NotificationContainer />
					</div>
				</HashRouter>
			</Provider>
		);
	}
}

export default App;
