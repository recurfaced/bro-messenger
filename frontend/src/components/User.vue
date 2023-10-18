<template>
    <div>
        <button @click="connect">Connect to Socket.IO Server</button>
        <div v-if="isConnected">
            <p>Connected to Socket.IO server</p>
            <button @click="sendMessage">Send Message</button>
            <p v-if="receivedMessage">Received message: {{ receivedMessage }}</p>
        </div>
        <div v-else>
            <p>Not connected to Socket.IO server</p>
        </div>
    </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { io } from "socket.io-client";

export default {
    name: "user-profile",
    setup() {
        const getCookie = (name) => {
            const cookieValue = document.cookie
                .split('; ')
                .find(row => row.startsWith(name + '='));
            if (cookieValue) {
                return cookieValue.split('=')[1];
            } else {
                return null;
            }
        };

        const socket = io("http://localhost:8084/connect", {
            path: "/connect",
            extraHeaders: {
                Authorization: `Bearer ${getCookie("token")}`
            }
        });

        const isConnected = ref(false);
        const receivedMessage = ref(null);

        socket.on("connect", () => {
            isConnected.value = true;
            console.log("Connected to Socket.IO server");
        });

        socket.on("message", (data) => {
            receivedMessage.value = data;
        });

        socket.on("disconnect", () => {
            isConnected.value = false;
            console.log("Disconnected from Socket.IO server");
        });

        const connect = () => {
            socket.connect();
        };

        const sendMessage = () => {
            const message = "Hello, server!";
            socket.emit("message", message);
        };

        onMounted(() => {
            connect();
        });

        return {
            isConnected,
            receivedMessage,
            connect,
            sendMessage,
        };
    },

};

</script>
