<template>
    <div>
        <button @click="connect">connect</button>
    </div>
</template>

<script type="module">
import { Client } from '@stomp/stompjs';

export default {
    name: "user-profile",
    methods: {
        connect: function() {
            const token = this.getCookie("token");
            const client = new Client({
                brokerURL:'ws://localhost:8084/user',
                connectHeaders:{
                    Authorization: 'Bearer ' + token,
                },
                onConnect:()=>{
                    client.subscribe('http://localhost:8084/topic/messages',
                    message => console.log(`Received: ${message.body}`)
                    );
                    client.publish({destination: 'http://localhost:8084/topic/messages',
                    body:'First Message'})
                }
            }
            );
            client.activate();
        },

        getCookie: function(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        },
    },
};

</script>
