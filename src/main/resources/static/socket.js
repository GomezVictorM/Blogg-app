import { store } from './store.js'

let ws;
let isConnected = false;
connect();

function connect() {
    ws = new WebSocket('ws://localhost:4000/your-socket-route');
    ws.onarticle = (e) => {
      let data = JSON.parse(e.data)

      if(data.article && store.state.currentTheme != null) {
        if(store.state.currentTheme.id === data.ThemeId) {
          console.log(data)
          store.commit('addToCurrentThemeArticles', data)
        }
      }
      showSomething(e.data);
    }
    ws.onopen = (e) => {
        isConnected = true;
    };

    ws.onclose = (e) => {
        console.log("Closing websocket...");
    };

  console.log("Connecting...");
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    isConnected = false;
    console.log("Disconnected");
}

//WTF Tony!
function sendSomething() {
    ws.send(JSON.stringify({firstname: "Hello World!" }));
}

function showSomething(message) {
    console.log(message);
}