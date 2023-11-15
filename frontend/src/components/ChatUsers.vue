<template>
    <div>
        <div id="greetings">{{ greetings }}</div>
        <p>Chat ID: {{ localChatId }}</p>
        <input v-model="name" placeholder="Введите сообщение" />
        <button @click="sendName">Отправить</button>
    </div>
</template>
<script>

import {getWsConnect} from "@/wsApi";

export default {

    props: ['chatId'],

    data() {
        return {
            ws: null,
            name: '',
            greetings: '',
            isConnected: false,
            localChatId: null,
        };
    },
    beforeRouteEnter(to, from, next) {
        const chatId = to.params.chatId;
        next(vm => {
            vm.localChatId = chatId;
        });
    },

    methods: {
        async connect() {
            try {
                const responseStatus = await getWsConnect();
                console.log(responseStatus)
                if (responseStatus === 200) {
                    this.ws = new WebSocket(`ws://localhost:8084/ws`);

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

        disconnect() {
            if (this.ws !== null) {
                this.ws.close();
            }
            this.setConnected(false);
            console.log('Disconnected');
        },
        sendName() {
            if (this.localChatId && this.ws.readyState === WebSocket.OPEN) {
                const message = {
                    type: 'subscribe',
                    chatId: this.localChatId,
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
