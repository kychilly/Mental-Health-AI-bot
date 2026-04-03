// src/ChatBox.jsx
import { useState } from "react";

export default function ChatBox() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");

  // asdijp
  const OPENAI_API_KEY = "";

  const sendMessage = async () => {
    if (!input.trim()) return;

    // Add user message to chat
    const userMessage = input.trim();
    setMessages(prev => [...prev, { sender: "user", text: userMessage }]);
    setInput("");

    try {
      // Call OpenAI API (for testing only)
      const response = await fetch("https://api.openai.com/v1/chat/completions", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${OPENAI_API_KEY}`,
        },
        body: JSON.stringify({
          model: "gpt-4o-mini",
          messages: [{ role: "user", content: userMessage }],
          max_tokens: 150,
        }),
      });

      const data = await response.json();
      const aiMessage = data.choices[0].message.content;

      // Add AI message to chat
      setMessages(prev => [...prev, { sender: "ai", text: aiMessage }]);

      // Speak AI message
      const utterance = new SpeechSynthesisUtterance(aiMessage);
      utterance.lang = "en-US";
      speechSynthesis.speak(utterance);

    } catch (err) {
      console.error("Error:", err);
      setMessages(prev => [...prev, { sender: "ai", text: "Error getting AI response." }]);
    }
  };

  return (
    <div style={{ width: "400px", margin: "20px auto", fontFamily: "Arial" }}>
      <div style={{
        border: "1px solid #ccc",
        height: "300px",
        overflowY: "auto",
        padding: "10px",
        marginBottom: "10px"
      }}>
        {messages.map((m, i) => (
          <div key={i} style={{ color: m.sender === "user" ? "blue" : "green", marginBottom: "5px" }}>
            {m.sender === "user" ? "You: " : "AI: "} {m.text}
          </div>
        ))}
      </div>
      <input
        type="text"
        value={input}
        onChange={e => setInput(e.target.value)}
        placeholder="Type your message..."
        onKeyPress={e => { if(e.key === "Enter") sendMessage(); }}
        style={{ width: "300px", padding: "5px" }}
      />
      <button onClick={sendMessage} style={{ padding: "5px 10px", marginLeft: "5px" }}>Send</button>
    </div>
  );
}