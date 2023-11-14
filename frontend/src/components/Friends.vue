<template>
    <div class="main">
        <MainMenu :friendsCount="localFriendsCount"
                  @showProfile="showProfile"
                  @showNews="showNews"
                  @showFriends="showFriends"
                  @showMessages="showMessages" />
        <UserInfo :username="localUsername"
                  avatarText="avatar" />
    </div>
    <div>
        <input type="text" v-model="searchTerm" placeholder="Поиск бро">
        <h1>Список друзей</h1>
        <ul>
            <li v-for="(friendName, friendId) in friendsList" :key="friendId">
                {{ friendName }}
                <button @click="sendMessage(friendId)">Написать bro</button>
            </li>
        </ul>

        <h1>Запросы на дружбу</h1>
        <ul>
            <li v-for="(friendRequest, friendId) in friendsListRequest" :key="friendId">
                {{ friendRequest }}
                <button @click="sendRequest(friendId)">Принять в бро</button>
            </li>
        </ul>
    </div>
</template>

<script>
import {getFriendsList, getFriendsListRequest,getChatId,addFriendRequest} from "@/api";
import MainMenu from "@/components/global/MainMenu.vue";
import UserInfo from "@/components/global/UserInfo.vue";

export default {
    components: {
        MainMenu,
        UserInfo,
    },
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
            console.log(friends)
            const requestFriends = await getFriendsListRequest();
            this.friendsList = friends;
            console.log(this.friendsList)
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
            console.log(friendId);

            try {
                const chatId = await getChatId(friendId);
                console.log(chatId);
                //this.$router.push({ name: 'chat-user', params: { chatId: chatId } });
            } catch (error) {
                console.error(error);
            }
        },
        async sendRequest(userId) {
            console.log(userId);

            try {
                const chatId = await addFriendRequest(userId);
                console.log(chatId);
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
.main {
    margin: 0 250px;
    display: flex;
}
</style>
