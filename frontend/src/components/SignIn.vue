<template>
    <div className="sex">
        <input type="text" v-model="email" placeholder="Email">
        <input type="password" v-model="password" placeholder="Password">
        <button @click="login">Login</button>
        <div>{{ message }}</div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            email: "",
            password: "",
            message: ""
        };
    },
    name: 'HelloWorld',
    props: {
        msg: String
    },
    methods: {
        login() {
            axios
                .post('/api/users/signin', {
                    email: this.email,
                    password: this.password
                },
                    {
                        headers: {
                            'Access-Control-Allow-Origin': '*',
                            'Content-Type': 'application/json',
                        },
                    }
                    )
                .then(response => {
                    var token = response.data.token;
                    document.cookie = `token=${token}; path=/`;
                    this.$router.push('/chat');
                })
                .catch(error => {
                    this.message = "Authentication failed. Please check your credentials.";
                    console.error(error);
                });
        }
    },
    mounted() {
    }
};
</script>

<style scoped>
.sex {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
}

input {
    margin-bottom: 10px;
    padding: 10px;
    font-size: 16px;
}

button {
    padding: 10px 20px;
    font-size: 16px;
    background-color: #4caf50;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #45a049;
}
</style>
