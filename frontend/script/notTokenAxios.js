import axios from "axios";

const notToken = axios.create({
    baseURL: 'http://www.meatteam.online'
});

export default notToken