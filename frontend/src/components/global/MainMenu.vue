<template>
    <div class="menu">
        <MenuButton label="Мой профиль" :onClick="showProfile" />
        <MenuButton label="Новости" :onClick="showNews" />
        <MenuButton :label="`Мои бро ${friendsCount}`" :onClick="showFriends" />
        <MenuButton label="Сообщения" :onClick="showMessages" />
        <!-- Другие кнопки меню -->
    </div>
</template>

<script>
import MenuButton from "@/components/global/MenuButton.vue";

export default {
    components: {
        MenuButton,
    },
    props: {
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
        // Методы для других кнопок меню
    },
};
</script>

<style scoped>
.menu {
    width: 200px;
    height: 100%;
    background-color: #f0f0f0;
    float: left;
}
</style>
