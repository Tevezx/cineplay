import api from './Api';

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

export const atualizarAvaliacao = async (idUsuario, idFilme, avaliacao) => {
    try {
        const response = await api.put(`/avaliacoes/${idUsuario}/${idFilme}`, avaliacao);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const deletarAvaliacao = async (idUsuario, idFilme) => {
    try {
        const response = await api.delete(`/avaliacoes/${idUsuario}/${idFilme}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};