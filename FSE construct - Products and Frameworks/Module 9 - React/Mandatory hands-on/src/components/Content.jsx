import React from 'react';

const Content = () => {
  return (
    <main style={{ padding: '3rem', maxWidth: '800px', margin: '0 auto', fontFamily: 'sans-serif' }}>
      <div style={{
          background: 'white',
          padding: '2rem',
          borderRadius: '12px',
          boxShadow: '0 10px 25px rgba(0,0,0,0.05)',
          border: '1px solid #f0f0f0'
      }}>
        <h2 style={{ color: '#182848' }}>Welcome to the Dashboard</h2>
        <p style={{ color: '#555', lineHeight: '1.6' }}>
          This is a placeholder for the application content. In a full implementation, 
          this section would fetch data from the Spring Boot REST API and display it here using React state.
        </p>
        <button style={{
            background: '#4b6cb7',
            color: 'white',
            border: 'none',
            padding: '10px 20px',
            borderRadius: '6px',
            cursor: 'pointer',
            fontSize: '1rem',
            marginTop: '1rem',
            transition: 'background 0.3s ease'
        }}>
          Fetch Data
        </button>
      </div>
    </main>
  );
};

export default Content;
