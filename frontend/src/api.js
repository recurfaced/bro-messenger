import axios from "axios";

const instance = axios.create({
    baseURL: "/api",
    headers: {
        "Content-Type": "application/json",
    },
});

const getToken = () => {
    return document.cookie
        .split("; ")
        .find(row => row.startsWith("token="))
        .split("=")[1];
};



export const getFriendsList = async () => {
    const token = getToken();
    const response = await instance.get(`/friends/list`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    return response.data;
};

export const getFriendsListRequest = async () => {
    const token = getToken();
    const response = await instance.get(`/friends/request`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    return response.data;
};
export const getChatId = async (friendId) => {
    const token = getToken();
    const response = await instance.post(`/chat/${friendId}`, null, {
        headers: {
            Authorization: `Bearer ${token}`,
        }
    });
    console.log("все прошло сексуально", response.data);
};



export const authUser = async (auth)=>{
    const response = await instance.post("/users/signin",
        auth,{
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
            },
        })
    console.log(response.data)
    return response.data;
};
export const getUserById = async () => {
    const token = getToken();
    const response = await instance.get(`/users/`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    return response.data;
};



