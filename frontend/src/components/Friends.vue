<template>
    <div>
        <h1>Список друзей</h1>
        <ul>
            <li v-for="friend in friendsList" :key="friend.id">
                {{ friend.username }}
                <button @click="sendMessage(friend.id)">Написать bro</button>
            </li>
        </ul>
    </div>
</template>

<script>
import { getFriendsList } from "@/api";

export default {
    name: "UserFriendsList",
    data() {
        return {
            friendsList: [],
        };
    },
    async mounted() {
        try {
            const friends = await getFriendsList(this.$route.params.userId);
            this.friendsList = friends;
        } catch (error) {
            console.error(error);
        }
    },
    methods: {
        sendMessage(friendId) {
            console.log(`Отправить сообщение другу с ID: ${friendId}`);
        },
    },
};
</script>

<style scoped>
ul {
    list-style-type: none;
}
</style>