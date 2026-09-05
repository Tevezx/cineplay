import api from './api';

export const cadastrarUsuario = async (usuario) => {
    try{
        const response = await api.post('/usuarios', usuario);
        return response.data;
    } catch(error){
        console.log("Erro no service do usuário: ", error.response?.data || error.message);
        throw error;
    }
}