import React, { Component } from 'react';
import Comment from './Comment';
import { Container, Divider, Header, Rating } from 'semantic-ui-react';
import { Rate } from 'antd';

class Poem extends Component {
	constructor(props) {
		super(props);
	}
	componentDidMount() {
		console.log(this.props);
	}

	render() {
		let content = (this.props.history.location.Poem.content = this.props.history.location.Poem.content.replace(
			/\n/g,
			'<br>'
		));
		return (
			<div class="poem">
				<div class="poem-container">
					<div class="poem-header">
						<Header as="h1">{this.props.history.location.Poem.title}</Header>
						<p style={{ fontSize: '1.5rem' }}>
							Rating:{' '}
							<Rating icon="star" defaultRating={this.props.history.location.Poem.Rating} maxRating={5} />
						</p>
					</div>
					<p>{this.props.history.location.Poem.author}</p>
					<img
						src={
							this.props.history.location.Poem.picByte
								? 'data:image/jpeg;base64,' + this.props.history.location.Poem.picByte
								: null
						}
						style={{ maxHeight: '700px', maxWidth: '700px', width: '100%', margin: '30px 0px 30px 0px' }}
					/>
					<p style={{ minHeight: 400 }} dangerouslySetInnerHTML={{ __html: content }} />

					<Divider />

					<Comment id={this.props.history.location.Poem.id} />
				</div>
			</div>
		);
	}
}

export default Poem;
