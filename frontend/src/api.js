import axios from "axios";

const instance = axios.create({
    baseURL: "/api",
    headers: {
        "Content-Type": "application/json",
    },
});

const getToken = () => {
    const token = document.cookie
        .split("; ")
        .find(row => row.startsWith("token="))
        .split("=")[1];
    return token;
};

export const getUserById = async (userId) => {
    const token = getToken();
    const response = await instance.get(`/users/${userId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    return response.data;
};

export const getFriendsCount = async (userId) => {
    const token = getToken();
    const response = await instance.get(`/friends/count/${userId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    return response.data.friendsCount;
};

export const getFriendsList = async (userId) => {
    const token = getToken();
    console.log(token)
    const response = await instance.get(`/friends/list/${userId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    const friendsMap = response.data.getFriendsListById;

    const dataArray = Object.entries(friendsMap).map(([id, username]) => ({
        id: Number(id),
        username: username
    }));

    console.log(dataArray);
    return dataArray;
};
export const getChatId = async (chatsRequest) => {
    const token = getToken()
        const response = await instance.post('/chat/',
            chatsRequest,
            {
            headers:{
                Authorization: `Bearer ${token}`,
            }
        });
    console.log("все прошло сексуально" + response.data)
};



