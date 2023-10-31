<template>
    <div class="main">
        <div class="menu">
            <button @click="showProfile">Мой профиль</button>
            <button @click="showProfile">Новости</button>
            <button @click="showFriends">Мои бро ({{ friendsCount }})</button>
            <button @click="showMessages">Сообщения</button>
            <button >Группы</button>
            <button >Музыка</button>
            <button >Видео</button>
            <button >Игры</button>
        </div>
        <div class="info">
            <div class="avatar">
                avatar block
            </div>
            <h1>{{ username }}'a профиль</h1>
        </div>
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

<style>
.main {
    margin: 0 250px;
}

.menu {
    width: 200px;
    height: 100%;
    background-color: #f0f0f0;
    float: left;
}

.menu button {
    width: 100%;
    padding: 10px;
    border: none;
    background-color: transparent;
    cursor: pointer;
    text-align: left;
}

.menu button:hover {
    background-color: #ccc;
}
.avatar {
    width: 150px;
    height: 150px;
    background-color: #ccc;
    text-align: center;
    line-height: 150px;
    font-size: 24px;
    color: #fff;
    float: left;
    margin-left: 12px;
}


.info {
    width: 700px;
    height: 660px;
    float: left;
    color: #fff;
    background-color: rebeccapurple;
    margin-left: 12px;

}

</style>
