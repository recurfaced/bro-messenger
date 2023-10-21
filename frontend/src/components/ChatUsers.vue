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
        this.websocket.onmessage = this.onMessage;
    },
    methods: {
        onMessage(event) {
            const message = JSON.parse(event.data);
            this.messages.push(message);
        },
        sendMessage() {
            if (this.newMessage) {
                const message = {
                    content: this.newMessage,
                };
                this.websocket.send(JSON.stringify(message));
                this.newMessage = '';
            }
        }
    },
    beforeUnmount() {
        if (this.websocket) {
            this.websocket.close();
        }
    }
};
</script>
