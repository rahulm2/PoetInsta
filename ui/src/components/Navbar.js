
import React from 'react';
import { Link } from 'react-router-dom';

class Navbar extends React.Component {


  constructor(props){

    super(props);

    this.state = { activeItem: 'Home' }
    
  }

  handleItemClick = (e, { name }) => {
    
    
    
    this.setState({ activeItem: name });

    
    
  } 


  render(){
  return (

    <nav>
   		<ul class="clearfix">
			<li class="active"><Link to="/">
                                Home
                            </Link></li>
			<li> <Link to="/UserProfile">
                                User Profile
                            </Link></li>
		</ul>
	</nav>

   
  )
}
}

export default Navbar;