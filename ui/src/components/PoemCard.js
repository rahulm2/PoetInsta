import React from 'react';
import upload from '../images/upload.png';
import { Redirect } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function PoemCard(props) {
	let onClick = function () {
		console.log('uo');
		return (
			<Redirect
				to={{
					pathname: `/Poem/${props.poem.id}`,
					Poem: {
						...props.poem,
					},
				}}
			/>
		);
	};

	return (
		<div class="poemCard">
			<div class="poemContainer">
				<p class="subtle">Recommended for you</p>
				<h4 style={{ padding: '0.2rem 0' }} onClick={() => onClick()}>
					<Link
						to={{
							pathname: `/Poem/${props.poem.id}`,
							Poem: {
								...props.poem,
							},
						}}
					>
						<b>{props.poem.title}</b>
					</Link>
				</h4>
				<p
					class="subtle"
					style={{
						overflow: 'hidden',
						minWidth: 0,
						textOverflow: 'ellipsis',
						paddingBottom: '0.2rem',
						whiteSpace: 'nowrap',
					}}
				>
					{props.poem.content}
				</p>
				<p>{props.poem.author}</p>
			</div>
			<img
				class="poem-card-image"
				src={props.poem.picByte ? 'data:image/jpeg;base64,' + props.poem.picByte : upload}
				alt="Avatar"
			/>
		</div>
	);
}
