import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

let stompClient = null;

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function connect() {
    const token = getCookie('token');
    console.log(token);

    const socket = new SockJS('http://localhost:8084/ws');
    stompClient = Stomp.over(socket);

    const headers = {
        'Authorization': 'Bearer ' + token
    };

    stompClient.connect(headers, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('http://localhost:8084/topic/public', message => {
            console.log(message.body);
        });
    });

    return stompClient;
}

function disconnect(client) {
    if (client !== null) {
        client.disconnect();
    }
    console.log("Disconnected");
}


export { connect, disconnect };
