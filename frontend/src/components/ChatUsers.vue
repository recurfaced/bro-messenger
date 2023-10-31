<template>
    <div>
        <div id="greetings">{{ greetings }}</div>
        <input v-model="name" placeholder="Введите сообщение" />
        <button @click="connect">Подключиться</button>
        <button @click="disconnect">Отключиться</button>
        <button @click="sendName">Отправить</button>
    </div>
</template>
<script>
export default {
    data() {
        return {
            ws: null,
            name: '',
            greetings: '',
            isConnected: false,
        };
    },
    methods: {
        connect() {
            this.ws = new WebSocket('ws://localhost:8084/ws');
            this.ws.onopen = () => {
                console.log("Connected");
            };
            this.ws.onmessage = this.showGreeting;
            this.setConnected(true);

        },
        disconnect() {
            if (this.ws !== null) {
                this.ws.close();
            }
            this.setConnected(false);
            console.log('Disconnected');
        },
        sendName() {
            if (this.ws !== null) {
                const message = {
                    name: this.name
                };
                this.ws.send(JSON.stringify(message));
            }
        },

        showGreeting(event) {
            this.greetings += ' ' + event.data;
        },
        setConnected(value) {
            this.isConnected = value;
        },
    },
    beforeUnmount() {
        this.disconnect();
    },
};
</script>
