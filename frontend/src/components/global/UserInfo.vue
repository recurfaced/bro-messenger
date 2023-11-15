<template>
    <div class="info">
        <Avatar :text="avatarText" />
        <h1>{{ username }}'a профиль</h1>
    </div>
</template>

<script>
import Avatar from "@/components/global/AvatarUser.vue";

export default {
    components: {
        Avatar,
    },
    props: {
        username: String,
        avatarText: String,
    },
};
</script>

<style scoped>
.info {
    float: left;
    color: #fff;
    width: 100%;
    background-color: rebeccapurple;
    margin-left: 12px;
}
</style>
