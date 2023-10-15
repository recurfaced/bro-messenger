 <template>
   <div class="registration">
       <input type="text" v-model="username">
       <input type="email" v-model="email">
       <input type="password" v-model="password">
   </div>
   <div>{{message}}</div>
   <button @click="registrationFunc"></button>

 </template>

 <script>
 import axios from "axios";

 export default {
     methods:{
         registrationFunc:function (){
             axios.post("/api/users/signup",{
                 username:this.username,
                 email:this.email,
                 password:this.password
             },{
                 headers: {
                 'Access-Control-Allow-Origin': '*',
                     'Content-Type': 'application/json',
             }})
                 .then((response)=> {
                     var token = response.data; // Предполагается, что токен находится в свойстве data объекта ответа
                     // Сохранение токена в куках с именем "token" и сроком жизни, например, 1 час
                     document.cookie = `token=${token}; path=/`;
                     console.log(response);
                 })
                 .catch(function (error) {
                     console.log(error);
                 });
         }
     },
     data(){
         return{
             message:"",
             username:"",
             email:"",
             password:""
         }
     }

 }

 </script>

 <style>
 .registration{
     display: flex;
     flex-direction: column;
 }
 </style>