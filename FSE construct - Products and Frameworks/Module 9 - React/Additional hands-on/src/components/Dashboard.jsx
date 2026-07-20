import React from 'react';

const Dashboard = () => {
  return (
    <div style={{
      padding: '40px',
      background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)',
      minHeight: '100vh',
      fontFamily: '"Inter", sans-serif'
    }}>
      <div style={{
        maxWidth: '1200px',
        margin: '0 auto',
        background: 'rgba(255, 255, 255, 0.9)',
        backdropFilter: 'blur(10px)',
        borderRadius: '20px',
        padding: '30px',
        boxShadow: '0 20px 40px rgba(0,0,0,0.1)'
      }}>
        <h1 style={{ color: '#2c3e50', borderBottom: '2px solid #3498db', paddingBottom: '10px' }}>
          Additional Exercises Dashboard
        </h1>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '20px', marginTop: '30px' }}>
          <div style={{ background: '#3498db', color: 'white', padding: '20px', borderRadius: '15px' }}>
            <h3>Users Online</h3>
            <p style={{ fontSize: '2rem', margin: '10px 0 0' }}>1,245</p>
          </div>
          <div style={{ background: '#e74c3c', color: 'white', padding: '20px', borderRadius: '15px' }}>
            <h3>Server Status</h3>
            <p style={{ fontSize: '2rem', margin: '10px 0 0' }}>99.9% Uptime</p>
          </div>
          <div style={{ background: '#2ecc71', color: 'white', padding: '20px', borderRadius: '15px' }}>
            <h3>API Latency</h3>
            <p style={{ fontSize: '2rem', margin: '10px 0 0' }}>24ms</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
