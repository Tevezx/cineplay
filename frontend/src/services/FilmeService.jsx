import api from './Api';

export const cadastrarFilme = async (filme) => {
    const response = await api.post('/filmes', filme);
    return response.data;
}

export const listarFilmes = async () => {
    const response = await api.get('/filmes');
    return response.data;
}

export const editarFilme = async (id, filme) => {
    const response = await api.put(`/filmes/${id}`, filme);
    return response.data;
}

export const deletarFilme = async (id) => {
    const response = await api.delete(`/filmes/${id}`);
    return response.data;
}