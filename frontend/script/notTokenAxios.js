import axios from "axios";

const notToken = axios.create({
    baseURL: 'http://localhost:8090'
    //http://api.meatteam.online
});

export default notToken