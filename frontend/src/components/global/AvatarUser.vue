<template>
    <div class="avatar">
        {{ text }}
    </div>
</template>

<script>
export default {
    props: {
        text: String,
    },
};
</script>

<style scoped>
.avatar {
    background-color: #ccc;
    text-align: center;
    line-height: 150px;
    font-size: 24px;
    color: #fff;
    float: left;
    padding: 80px;
    margin: 20px;
}
</style>
