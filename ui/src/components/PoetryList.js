import { Component } from 'react';
import React from 'react';
import { Link } from 'react-router-dom';

class PoetryList extends Component {
	constructor(props) {
		super(props);
	}

	storeId = (id) => {
		window.localStorage.setItem('poemId', id);
		console.log(id);
	};

	handleClick = (index) => {
		this.storeId(index);
	};

	render() {
		let Poems = this.props.Poems.map((Poem, index) => (
			<div
				class="poem-card"
				style={{ textOverflow: 'ellipsis', overflow: 'hidden', whiteSpace: 'nowrap' }}
				key={index}
			>
				<div>
					<Link
						to={{
							pathname: `/Poem/${Poem.id}`,
							Poem: {
								...Poem,
								author: this.props.author,
							},
						}}
					>
						<b>{Poem.title}</b>
					</Link>
				</div>
				<div>{this.props.author}</div>
				<div>{Poem.content}</div>
			</div>
		));

		return (
			<div>
				<h2 class="poem-user">Poems created by You</h2>
				<div class="poemList">{Poems}</div>
			</div>
		);
	}
}

export default PoetryList;
