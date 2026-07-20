import React from 'react';

const Header = () => {
  return (
    <header style={{ 
        padding: '2rem', 
        background: 'linear-gradient(90deg, #4b6cb7 0%, #182848 100%)', 
        color: 'white',
        textAlign: 'center',
        boxShadow: '0 4px 6px rgba(0,0,0,0.1)'
    }}>
      <h1 style={{ margin: 0, fontSize: '2.5rem', fontFamily: 'sans-serif' }}>Mandatory React Hands-on</h1>
      <p style={{ margin: '10px 0 0', opacity: 0.8 }}>Spring Boot & React Integration Project</p>
    </header>
  );
};

export default Header;
