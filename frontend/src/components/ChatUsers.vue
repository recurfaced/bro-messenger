<template>
    <div>
        <ul>
            <li v-for="message in messages" :key="message.id">
                {{ message.content }}
            </li>
        </ul>
        <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Введите сообщение" />
    </div>
</template>

<script>
export default {
    data() {
        return {
            websocket: null,
            messages: [],
            newMessage: ''
        };
    },
    mounted() {
        this.websocket = new WebSocket('ws://localhost:8084/ws');
        this.websocket.onopen = () => {
            console.log("WebSocket connected");
        };
        this.websocket.onmessage = this.onMessage;
    },
    methods: {
        onMessage(event) {
            console.log("Entering onMessage");
            const message = JSON.parse(event.data);
            console.log("onMessage", message);
            this.messages.push(message);
        },
        sendMessage() {
            if (this.newMessage) {
                const message = {
                    content: this.newMessage,
                    sender:"852",
                    chatId:"103",
                    createdAt:"2023-10-26 10:00:00.000000",
                };
                console.log(message)
                this.websocket.send("ws://localhost:8084/app/ws", {}, JSON.stringify(message));
                this.newMessage = '';
            }
        }
    },
    beforeUnmount() {
        if (this.websocket) {
            console.log("beforeUnmount")
            this.websocket.close();
        }
    }
};
</script>
