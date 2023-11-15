<template>
    <button @click="onClick">{{ label }}</button>
</template>

<script>
export default {
    props: {
        label: String,
        onClick: Function,
    },
};
</script>

<style scoped>
button {
    width: 100%;
    padding: 10px;
    border: none;
    background-color: transparent;
    cursor: pointer;
    text-align: left;
}

button:hover {
    background-color: #ccc;
}
</style>
