import api from '../api';

export const buscarAvaliacoes = async () => {
    try {
        const response = await api.get('/avaliacoes');
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const avaliarFilme = async (avaliacao) => {
    try {
        const response = await api.post('/avaliacoes', avaliacao);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const atualizarAvaliacao = async (id, avaliacao) => {
    try {
        const response = await api.put(`/avaliacoes/${id}`, avaliacao);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const deletarAvaliacao = async (id) => {
    try {
        const response = await api.delete(`/avaliacoes/${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};