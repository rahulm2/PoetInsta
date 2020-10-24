import React from 'react';
import { Card, Feed } from 'semantic-ui-react';
import userImage from '../images/userImage.png';

const Activities = (props) => {
	let activities = props.activities.map((activity, index) => (
		<Feed.Event>
			<Feed.Label image={props.imageURL ? 'data:image/jpeg;base64,' + props.imageURL : userImage} />
			<Feed.Content>
				<Feed.Date content="1 day ago" />
				<Feed.Summary>{activity.description}</Feed.Summary>
			</Feed.Content>
		</Feed.Event>
	));

	return (
		<div className="activities">
			<Card.Content style={{ padding: '10px' }}>
				<Card.Header className="activity-header">Recent Activities</Card.Header>
			</Card.Content>
			<Card.Content>
				<Feed>{activities}</Feed>
			</Card.Content>
		</div>
	);
};

export default Activities;
