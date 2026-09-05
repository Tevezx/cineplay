import axios from 'axios';
import { URL_API } from '../constants/urlApi';

const api = axios.create({
    baseURL: URL_API,
    headers: {
        'Content-Type': 'application/json',
    }
});

export default api;

