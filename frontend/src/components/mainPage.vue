<template>
    <div>
        <h1>{{ username }}'a профиль</h1>
        <p>Друзья: {{ friendsCount }}</p>
        <button @click="showProfile">Мой профиль</button>
        <button @click="showMessages">Сообщения</button>
        <button @click="showFriends">Друзья</button>
    </div>
</template>

<script>
import { getFriendsCount, getUserById } from "@/api";

export default {
    data() {
        return {
            username: "",
            friendsCount: 0,

        };
    },
    methods: {
        showProfile() {
            console.log("Показать профиль");
        },
        showMessages() {
            console.log("Показать сообщения");
        },
        showFriends() {
            this.$router.push({ name: 'UserFriendsList', params: { userId: this.$route.params.userId } });
        },

        async fetchData() {
            const userId = this.$route.params.userId;
            try {
                const [userResponse, friendsCount] = await Promise.all([
                    getUserById(userId),
                    getFriendsCount(userId),
                ]);

                this.username = userResponse.username;
                this.friendsCount = friendsCount;
            } catch (error) {
                console.error(error);
                this.$router.push('/error');
            }
        },
    },
    mounted() {
        this.fetchData();
    },
};
</script>

<style scoped>
</style>
