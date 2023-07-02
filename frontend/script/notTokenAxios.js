import axios from "axios";

const notToken = axios.create({
    baseURL: 'http://api.meatteam.online'
});

export default notToken