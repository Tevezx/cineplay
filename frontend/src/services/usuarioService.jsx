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

export const loginUsuario = async (credenciais) => {
    try{
        const response = await api.post('/usuarios/login', credenciais);
        return response.data;
    } catch(error){
        console.log("Erro no service de login: ", error.response?.data || error.message);
        throw error;
    }
}
