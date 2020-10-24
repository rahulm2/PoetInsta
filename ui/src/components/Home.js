import React from 'react';
import '../App.css';
import { connect } from 'react-redux';
import { getBacklog } from '../actions/poemActions';
import PoemCard from './PoemCard';
import Sidebar from './Sidebar';

class Home extends React.Component {
	constructor(props) {
		super(props);
	}

	componentDidMount() {
		console.log('mount');
		this.props.getBacklog();
	}
	componentWillReceiveProps(nextProps) {
		if (this.props.poems !== nextProps.poems) {
			console.log(nextProps.poems.Poems);
		}

		console.log(this.props.poems.Poems);
	}

	componentWillUnmount() {
		console.log('unmount');
	}
	render() {
		const poems = this.props.poems.Poems.map((row) => ({
			key: row.id, // here

			title: row.title,
			author: row.author,
			content: row.content,
			id: row.id,
			rating: row.rating,
			picByte: row.picByte,
		}));

		let poet = {
			content:
				'afbkjdsfbdasffffffffffffffdjksfdskfjdksfkj nfsddddddddddddddddddsflsdfnldksnfnlkdsnfdsnflndslkfnldksnflkdsnlkfndsklnfdsflkdsfld nflsdkndsklfdskfdsfn',
			title: 'Title',
			author: 'rahul',
		};
		let poemCards = poems.map((poem, index) => <PoemCard poem={poem} />);

		return (
			<div class="home">
				<div class="poems">
					<h2 style={{ padding: '2px 16px 20px' }}>Poems List</h2>
					{poemCards}
					<PoemCard poem={poet} />
					<PoemCard poem={poet} />
					<PoemCard poem={poet} />
					<PoemCard poem={poet} />
					<PoemCard poem={poet} />
				</div>
				<Sidebar />
			</div>
		);
	}
}

const mapStateToProps = (state) => ({
	poems: state.poems,
});

export default connect(mapStateToProps, { getBacklog })(Home);
