import axios from "axios";


const instance = axios.create({
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

export const getWsConnect = async () => {
    const token = getToken();
    try {
        const response = await instance.get("/ws-connect", {
            headers: {
                Authorization: `Bearer ${token}`,

            },
        });

        return response.status;

    } catch (error) {
        console.error("Произошла ошибка при установке соединения:", error);
        return null;
    }
};