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
</template>

<script>
import MainMenu from "@/components/global/MainMenu.vue";
import UserInfo from "@/components/global/UserInfo.vue";
import { getUserById } from "@/api";
export default {
    components: {
        MainMenu,
        UserInfo,
    },
    data() {
        return {
            localUsername: "",
            localFriendsCount: 0,
        };
    },
    props: {
        username: String,
        friendsCount: Number,
    },
    methods: {
        showProfile() {
            console.log("Показать профиль");
        },
        showNews() {
            console.log("Показать новости");
        },
        showMessages() {
            console.log("Показать сообщения");
        },
        showFriends() {
            this.$router.push({ name: "UserFriendsList" });
        },
    },
    async beforeMount() {
        try {
            const userResponse = await getUserById();
            this.localUsername = userResponse.username;
            this.localFriendsCount = userResponse.friends;
        } catch (error) {
            console.error(error);
            this.$router.push('/error');
        }
    },
};
</script>

<style scoped>
.main {
    margin: 0 250px;
    display: flex;
}
</style>
