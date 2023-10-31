<template class="">

    <div class="wrapper">
        <div class="header">
            <h3 class="sign-in">Sign in</h3>
            <div class="button">
                Register
            </div>
        </div>
            <div class="">
                <input type="text" v-model="email" placeholder="Email">
                <input type="password" v-model="password" placeholder="Password">
                <div class="error">{{ message }}</div>
            </div>

        <div class="clear"></div>
        <form action="#">
            <div>
                <input type="submit" value="Sign in" @click="login"/>
            </div>
            <div class="radio-check">
                <input type="radio" class="radio-no" id="no" name="remember" value="no" checked>
                <label for="no"><i class="fa fa-times"></i></label>
                <input type="radio" class="radio-yes" id="yes" name="remember" value="yes">
                <label for="yes"><i class="fa fa-check"></i></label>
                <span class="switch-selection"></span>
            </div>
            <span class="check-label">Remember me</span>
            <span class="forgot-label">Lost your password?</span>
            <div class="clear"></div>
        </form>
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
                    var userId = response.data.userId;
                    console.log("UserID:", userId);
                    this.$store.commit('setUserId', userId);
                    document.cookie = `token=${token}; path=/`;
                    this.$router.push('/main');
                    this.$router.push({ name: 'mainPage', params: { userId: userId } });
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

<style >
@import url(https://fonts.googleapis.com/css?family=Roboto:400,300,500);
*, *:after, *:before {
    box-sizing: border-box;
}

html, template {
    background: #1a1f25;
    font-family: 'Roboto', sans-serif;
}

.clear {
    clear: both;
}


.wrapper {
    width: 300px;
    margin: 12% auto;

}

form {
    position: relative;
}

.clear {
    clear: both;
}

.sign-in {
    float: left;
    color: white;
    font-size: 1.3em;
}
.error {
    float: left;
    color: indianred;
    font-size: 1.3em;
}
.header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.button {
    float: right;
    color: #7f8084;
    border: 1px solid #22272d;
    padding: 7px 15px;
    border-radius: 3px;
    font-size: 0.8em;
    cursor: pointer;
    margin-top: 20px;
}

.button:hover {
    color: #d3d3d3;
}

input {
    width: 100%;
    padding: 5px;
    height: 40px;
    border-radius: 3px;
    margin: 5px 0;
    outline: none;
}

input[type="text"]:focus, input[type="password"]:focus {
    border: 1px solid white;
    box-shadow: none;
}

.user-input:focus .user {
    background: white !important;
}

input[type="text"] {
    background: transparent;
    border: 2px solid #22272d;
    padding-left: 45px;
    color: #e6b333;
}

input[type="password"] {
    background: transparent;
    border: 2px solid #22272d;
    padding-left: 45px;
    color: #e6b333;
}

input[type="submit"] {
    background: #e6b333;
    border: none;
    color: white;
    text-align: center;
    font-size: 0.8em;
    cursor: pointer;
}

input[type="radio"] {
    display: none;
}

.radio-check {
    width: 50px;
    height: 20px;
    border: 1px solid #22272d;
    border-radius: 30px;
    margin-top: 10px;
    float: left;
    text-align: center;
    padding: 4px 0;
    color: #e6b333;
    font-size: 0.65em;
    position: relative;
}
.radio-check label {
    margin: 0 2px;
    cursor: pointer;
}
.radio-check .switch-selection {
    display: block;
    position: absolute;
    width: 11px;
    height: 11px;
    border-radius: 50%;
    background: #babbbd;
    margin-top: -11px;
    margin-left: 11px;
    -webkit-transition: 0.2s ease-out;
    transition: 0.2s ease-out;
}

.radio-yes:checked ~ .switch-selection {
    margin-left: 25px !important;
}

.check-label {
    font-size: 0.8em;
    color: #7f8084;
    margin-top: 10px;
    float: left;
    padding: 3px 0;
    margin-left: 10px;
}

.forgot-label {
    font-size: 0.8em;
    color: #7f8084;
    margin-top: 10px;
    float: right;
    padding: 3px 0;
    cursor: pointer;
}

</style>
