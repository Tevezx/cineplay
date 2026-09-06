import api from './Api';

export const cadastrarFilme = async (filme) => {
    const response = await api.post('/filmes', filme);
    return response.data;
}

export const listarFilmes = async () => {
    const response = await api.get('/filmes');
    return response.data;
}