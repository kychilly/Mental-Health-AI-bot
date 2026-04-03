import React, { useState, useRef } from 'react';

import ChatBox from "./ChatBox";

function App() {
  return (
    <div>
      <h1>Mental Health AI Chat</h1>
      <ChatBox />
    </div>
  );
}

export default App;

function App() {
  const [isTalking, setIsTalking] = useState(false);
  const audioRef = useRef(null);

  // This function makes the logo pulse and plays the audio
  const startAIResponse = () => {
    if (audioRef.current) {
      audioRef.current.play();
      setIsTalking(true);
    }
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>Mental Health AI</h1>

      {/* This is the pulsing "Logo" face */}
      <div
        style={{
          ...styles.logo,
          animation: isTalking ? 'pulse 1.5s infinite ease-in-out' : 'none',
          boxShadow: isTalking ? '0 0 50px #00d2ff' : '0 0 10px #555'
        }}
      >
        {/* You can replace this emoji with an <img> tag later! */}
        <span style={{ fontSize: '50px' }}>🧠</span>
      </div>

      <p style={styles.status}>
        {isTalking ? "AI is speaking..." : "Ready to listen"}
      </p>

      {/* Temporary audio for testing */}
      <audio
        ref={audioRef}
        onEnded={() => setIsTalking(false)}
        src="https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
      />

      <button onClick={startAIResponse} style={styles.button}>
        Simulate AI Voice
      </button>

      <style>{`
        @keyframes pulse {
          0% { transform: scale(1); filter: brightness(1); }
          50% { transform: scale(1.15); filter: brightness(1.5); }
          100% { transform: scale(1); filter: brightness(1); }
        }
      `}</style>
    </div>
  );
}

const styles = {
  container: {
    height: '100vh',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#0f172a', // Dark blue/black
    color: 'white',
    fontFamily: 'system-ui, sans-serif',
  },
  title: { marginBottom: '40px', letterSpacing: '1px' },
  logo: {
    width: '160px',
    height: '160px',
    borderRadius: '50%',
    backgroundColor: '#1e293b',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    border: '3px solid #38bdf8',
    transition: 'all 0.4s ease',
  },
  status: { marginTop: '30px', color: '#94a3b8' },
  button: {
    marginTop: '50px',
    padding: '12px 30px',
    borderRadius: '30px',
    border: 'none',
    backgroundColor: '#38bdf8',
    color: '#0f172a',
    fontWeight: 'bold',
    cursor: 'pointer',
    fontSize: '16px'
  }
};

export default App;