<template>
    <div>
        <h1>Список друзей</h1>
        <ul>
            <li v-for="(friendUsername, friendId) in friendsList" :key="friendId">
                {{ friendUsername }}
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
            friendsList: {}, // Объект для хранения id и username друзей
        };
    },
    async mounted() {
        try {
            // Получаем список друзей пользователя
            const friends = await getFriendsList(this.$route.params.userId);
            this.friendsList = friends;
            console.log("друзья" + this.friendsList)
        } catch (error) {
            console.log(error)
        }
    },
};
</script>

<style scoped>
</style>
