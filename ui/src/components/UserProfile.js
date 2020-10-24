import React from 'react';
import '../App.css';
import PoetryList from './PoetryList';
import Profile from './Profile';
import Activities from './Activities';
import { connect } from 'react-redux';
import { getUser } from '../actions/userActions';

class UserProfile extends React.Component {
	constructor(props) {
		super(props);
	}

	AddPoem = () => {
		console.log('Add Poem');
		this.props.history.push('/AddPoem');
	};

	componentDidMount() {
		console.log('mount');
		this.props.getUser();
		console.log(this.props.getUser());
	}

	render() {
		return (
			<div class="userProfile">
				<div class="profile-section-left">
					<Profile userDetails={this.props.user.User} />
					<Activities imageURL={this.props.user.User.picByte} activities={this.props.user.User.activities} />
				</div>
				<PoetryList
					author={this.props.user.User.email}
					Poems={this.props.user.User.poems}
					history={this.props.history}
				/>
			</div>
		);
	}
}

const mapStateToProps = (state) => ({
	user: state.user,
});

export default connect(mapStateToProps, { getUser })(UserProfile);
