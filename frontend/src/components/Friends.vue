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
import { getChatId } from "@/api";

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
        async sendMessage(friendId) {
            const userId = parseInt(this.$route.params.userId, 10);
            console.log(userId);
            console.log(friendId);

            const createChat = {
                userId: userId,
                friendId: friendId,
            };
            console.log(createChat)

            try {
                await getChatId(createChat);
                this.$router.push({ name: 'chat-user', params: { id: friendId, userId: userId } });
            } catch (error) {
                console.error(error);
            }
        },
    },
};
</script>

<style scoped>
ul {
    list-style-type: none;
}
</style>
