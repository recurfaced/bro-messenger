<template>
    <div>
        <div id="greetings">{{ greetings }}</div>
        <input v-model="name" placeholder="Введите сообщение" />
        <button @click="sendName">Отправить</button>
    </div>
</template>
<script>

import {getWsConnect} from "@/wsApi";

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
        async connect() {
            try {
                const responseStatus = await getWsConnect();
                console.log(responseStatus)
                if (responseStatus === 101) {
                    const token = this.getToken();
                    this.ws = new WebSocket(`ws://localhost:8084/ws?token=${token}`);
                    this.ws.onopen = () => {
                        console.log("Connected");
                    };
                    this.ws.onmessage = this.showGreeting;
                    this.setConnected(true);
                } else {
                    console.error("Не удалось установить WebSocket соединение.");
                }
            } catch (error) {
                console.error("Произошла ошибка:", error);
            }
        },

        getToken() {
            return document.cookie
                .split("; ")
                .find(row => row.startsWith("token="))
                .split("=")[1];
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
    mounted() {
        this.connect();
    },
    beforeUnmount() {
        this.disconnect();
    },
};
</script>
