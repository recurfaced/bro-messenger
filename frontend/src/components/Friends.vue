<template>
    <div>
        <input type="text" v-model="searchTerm" placeholder="Поиск бро">
        <h1>Список друзей</h1>
        <ul>
            <li v-for="friend in friendsList" :key="friend.id">
                {{ friend }}
                <button @click="sendMessage(friend.id)">Написать bro</button>
            </li>
        </ul>

        <h1>Запросы на дружбу</h1>
        <ul>
            <li v-for="friendRequest in friendsListRequest" :key="friendRequest.id">
                {{ friendRequest }}
                <button @click="sendMessage(friendRequest.id)">Принять в бро</button>
            </li>
        </ul>
    </div>
</template>

<script>
import {getFriendsList, getFriendsListRequest,getChatId} from "@/api";

export default {
    name: "UserFriendsList",
    data() {
        return {
            friendsList: [],
            friendsListRequest: [],
            searchTerm: '',
        };
    },
    async mounted() {
        try {
            const friends = await getFriendsList();
            const requestFriends = await getFriendsListRequest();
            this.friendsList = friends;
            this.friendsListRequest = requestFriends;
        } catch (error) {
            console.error(error);
        }
    },
    computed: {
        filteredFriendsList() {
            const searchTerm = this.searchTerm.toLowerCase();
            return this.friendsList.filter(friend => friend.username.toLowerCase().includes(searchTerm));
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
