import { useState } from 'react';
import { cadastrarUsuario } from '../services/usuarioService';

const formatarCPF = (value) => {
    return value
        .replace(/\D/g, "")
        .replace(/(\d{3})(\d)/, "$1.$2") 
        .replace(/(\d{3})(\d)/, "$1.$2") 
        .replace(/(\d{3})(\d{1,2})$/, "$1-$2") 
        .substring(0, 14); 
};

export function FormCadastro() {
    const [dados, setDados] = useState({
        cpf: '',
        nome: '',
        email: '',
        senha: '',
    });

    const cadastrar = async (event) => {
        event.preventDefault();
        try{
            await cadastrarUsuario(dados);
            alert('Usuário cadastrado com sucesso!');
        }
        catch(error){
            alert('Erro ao cadastrar usuário: ' + (error.response?.data?.message || error.message));
        }
    }

    return (
        <form onSubmit={cadastrar}>
            <div>
                <label>CPF:</label>
                <input type="text" placeholder='000.000.000-00' name="cpf" value={dados.cpf} onChange={(e)=>setDados({...dados, cpf: formatarCPF(e.target.value)})} />
            </div>
            <div>
                <label>Nome:</label>
                <input type="text" placeholder='Digite seu nome...' name="nome" value={dados.nome} onChange={(e)=>setDados({...dados, nome: e.target.value})} />
            </div>
            <div>
                <label>Email:</label>
                <input type="email" placeholder='Digite seu email...' name="email" value={dados.email} onChange={(e)=>setDados({...dados, email: e.target.value})} />
            </div>
            <div>
                <label>Senha:</label>
                <input type="password" placeholder='Digite sua senha...' name="senha" value={dados.senha} onChange={(e)=>setDados({...dados, senha: e.target.value})} />
            </div>
            <button type='submit'>Cadastrar</button>
        </form>
    );
}
