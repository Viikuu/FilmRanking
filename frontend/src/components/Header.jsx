import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/add-film">Add Film</Link>
        {/* Other navigation links */}
      </nav>
    </header>
  );
}

export default Header;
