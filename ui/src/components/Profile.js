import React from 'react';
import Upload from './Upload';

const Profile = (props) => (
	<div className="profile" style={{ marginRight: '5px', marginBottom: '10px' }}>
		<Upload imageURL={props.userDetails.picByte} />
		<div class="profile-content">
			<div style={{ fontSize: '26px', lineHeight: '1.25', fontWeight: '600' }}>
				{props.userDetails.firstName}rahul
			</div>
			<div style={{ fontSize: '20px', lineHeight: '24px', fontWeight: '300' }}>
				{props.userDetails.email}mehrar12@gmail.com
			</div>
		</div>
	</div>
);

export default Profile;
