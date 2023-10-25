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
        console.log("mounted")
        this.websocket = new WebSocket('ws://localhost:8084/ws');
        console.log(this.websocket)

        this.websocket.onopen = () => {
            console.log("WebSocket connected");
            this.websocket.send("/app/ws");
        };

        this.websocket.onmessage = this.onMessage;
    },
    methods: {
        onMessage(event) {
            const message = JSON.parse(event.data);
            console.log("onMessage")
            this.messages.push(message);
        },
        sendMessage() {
            console.log("sendMessage")
            if (this.newMessage) {
                const message = {
                    content: this.newMessage,
                    sender:"user",
                };
                console.log(message)
                this.websocket.send(JSON.stringify(message));
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
