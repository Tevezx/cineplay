import api from './api';

export const cadastrarFilme = async (filme) => {
    const response = await api.post('/filmes', filme);
    return response.data;
}