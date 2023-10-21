<template>
    <div>
        <button @click="connectWebSocket">Подключиться</button>
        <button @click="disconnectWebSocket">Отключиться</button>
    </div>
</template>

<script>
import { ref } from 'vue';
import { connect, disconnect } from '@/socket';

export default {
    setup() {
        const stompClient = ref(null);
        const isConnected = ref(false);

        const connectWebSocket = () => {
            stompClient.value = connect({
            });
            isConnected.value = true;
        };

        const disconnectWebSocket = () => {
            disconnect(stompClient.value);
            isConnected.value = false;
        };

        return {
            connectWebSocket,
            disconnectWebSocket,
            isConnected
        };
    },
};
</script>
