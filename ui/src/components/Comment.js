import React from 'react';
import { Button, Comment, Form } from 'semantic-ui-react';
import { connect } from 'react-redux';
import { getComments, addComment } from '../actions/poemActions';
import userImage from '../images/userImage.png';

class Comments extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			comment: {
				ImageSrc: '',
				content: '',
				metadata: '1 day ago ',
			},
		};
	}

	componentDidMount() {
		this.props.getComments(this.props.id);
	}

	handleChange = (e, { name, value }) => {
		let commentEdit = { ...this.state.comment };
		commentEdit.content = value;
		this.setState({ comment: commentEdit });
	};

	handleSubmit = () => {
		const { comment } = this.state;
		console.log(comment);
		console.log(this.props.id);
		this.props.addComment(comment, this.props.id);
		let commentEdit = { ...this.state.comment };
		commentEdit.content = '';
		this.setState({ comment: commentEdit });
	};

	render() {
		let commentsList = this.props.comments.Comments.map((comment, index) => {
			return (
				<Comment>
					<Comment.Avatar as="a" src={comment.userImage ? comment.userImage : userImage} />
					<Comment.Content>
						<Comment.Author>{comment.email}</Comment.Author>
						<Comment.Metadata>
							<div>{comment.metadata}</div>
						</Comment.Metadata>
						<Comment.Text>
							<p>{comment.content}</p>
						</Comment.Text>
						<Comment.Actions>
							<Comment.Action>Reply</Comment.Action>
						</Comment.Actions>
					</Comment.Content>
				</Comment>
			);
		});

		return (
			<Comment.Group>
				{commentsList}

				<Form onSubmit={this.handleSubmit}>
					<Form.TextArea
						name="Content"
						value={this.state.comment.content}
						onChange={this.handleChange}
						style={{ marginTop: '2em' }}
					/>
					<Button content="Add Comment" labelPosition="left" icon="edit" primary />
				</Form>
			</Comment.Group>
		);
	}
}

const mapStateToProps = (state) => ({
	comments: state.poems,
});

export default connect(mapStateToProps, { getComments, addComment })(Comments);
