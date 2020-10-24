import React, { Component } from 'react';
import { connect } from 'react-redux';
import { addPoem } from '../actions/poemActions';
import PoemImageUpload from './PoemImageUpload';
import upload from '../images/upload.png';

class AddPoem extends Component {
	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);

		this.state = {
			Title: '',
			Content: '',
			imageURL: upload,
			imageFormData: '',
		};
	}

	handleImageUpload = (uploadedURL) => {
		this.setState({ imageURL: URL.createObjectURL(uploadedURL), imageFormData: uploadedURL });
	};

	handleChange = (e) => this.setState({ [e.target.name]: e.target.value });

	handleSubmit = (e) => {
		e.preventDefault();

		const uploadFormData = new FormData();
		uploadFormData.append('imageFile', this.state.imageFormData);
		let Poem = { title: this.state.Title, content: this.state.Content };
		uploadFormData.append('poem', JSON.stringify(Poem));
		console.log(Poem);
		this.props.addPoem(uploadFormData, this.props.history);
	};

	render() {
		const { Title, Content } = this.state;
		return (
			<div class="add-poem-container">
				<form class="add-poem-form" onSubmit={this.handleSubmit}>
					<label class="add-poem-form-label" for="Title">
						Title
					</label>
					<input
						class="add-poem-inputs"
						placeholder="Title"
						name="Title"
						value={Title}
						onChange={this.handleChange}
					/>

					<PoemImageUpload imageURL={this.state.imageURL} handleImageUpload={this.handleImageUpload} />

					<label class="add-poem-form-label" for="Content">
						Content
					</label>
					<textarea
						class="add-poem-inputs"
						placeholder="What's on your mind..."
						name="Content"
						value={Content}
						onChange={this.handleChange}
						style={{ minHeight: 500 }}
					/>

					<button class="add-poem-button" type="submit">
						Submit
					</button>
				</form>
			</div>
		);
	}
}

export default connect(null, { addPoem })(AddPoem);
