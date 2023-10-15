<template>
  <div class="sex" >
      <input type="text" v-model="email">
      <input type="text" v-model="password">
  </div>
  <div>{{message}}</div>
  <button @click = "login"> </button>
</template>

<script>
import axios from "axios";

export default {
    methods:{
        login:function (){
            axios.post('/api/users/signin', {
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
                .then((response)=> {
                    var token = response.data.token;
                    document.cookie = `token=${token}; path=/`;
                    this.$router.push('/user');

                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    data(){
        return {
            message:"evsik@gmail.com",
            email:"",
            password:""
        }
    },
    name: 'HelloWorld',
    props: {
        msg: String
    }
}
</script>
<style>
.sex{
    display: flex;
    flex-direction: column;
}
</style>

