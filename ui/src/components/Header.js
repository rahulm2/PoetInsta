import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../images/logoPoem.png';
import menu from '../images/menu.png';
import axios from 'axios';

class Header extends React.Component {
	constructor(props) {
		super(props);

		this.state = { menuToggle: false };
	}

	handleItemClick = (e) => {
		this.setState((prevState) => ({
			menuToggle: !prevState.menuToggle,
		}));
	};

	handleLogout = () => {
		axios({ method: 'get', url: '/logout' })
			.then((reponse) => {
				window.location.href = '/login';
			})
			.catch((error) => console.log('BAD', error.response.data.message));
	};

	handleMenuToggle = () => {
		this.setState((prevState) => ({
			menuToggle: !prevState.menuToggle,
		}));
	};

	render() {
		return (
			<header>
				<div class="header-title">
					<div style={{ display: 'flex', alignItems: 'center' }}>
						<img src={logo} alt="Avatar" />
						<h2>PoetInsta</h2>
					</div>
					<img class="hamburger-menu" src={menu} onClick={this.handleMenuToggle} />
				</div>

				<div class={this.state.menuToggle ? 'menu' : 'menu hamburger-menu-toggle'}>
					{this.state.menuToggle ? <hr /> : null}
					<Link className="logout" to="/" onClick={this.handleItemClick}>
						Home
					</Link>
					{this.state.menuToggle ? <hr /> : null}
					<Link className="logout" to="/UserProfile" onClick={this.handleItemClick}>
						Profile
					</Link>
					{this.state.menuToggle ? <hr /> : null}
					<Link className="logout" to="/AddPoem" onClick={this.handleItemClick}>
						<button>Add a Poem</button>
					</Link>
					{this.state.menuToggle ? <hr /> : null}
					<Link className="logout" onClick={this.handleLogout} to="/">
						Log out
					</Link>
				</div>
			</header>
		);
	}
}

export default Header;
