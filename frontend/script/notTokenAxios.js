import axios from "axios";

const notToken = axios.create({
    baseURL: 'http://localhost:8090'
});

export default notToken