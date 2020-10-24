import React from 'react';
import logo from '../images/logoPoem.png';

class Header extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<header>
				<div style={{ display: 'flex', alignItems: 'center' }}>
					<img src={logo} alt="Avatar" />
					<h2>PoetInsta</h2>
				</div>
			</header>
		);
	}
}

export default Header;
