import { useState } from 'react';
import { loginUsuario } from '../services/usuarioService';
import styles from '../styles/formCadastro.module.css';
import imagemCadastro from '../assets/imagem_cadastro.jpg';

const formatarCPF = (value) => {
    return value
        .replace(/\D/g, "")
        .replace(/(\d{3})(\d)/, "$1.$2") 
        .replace(/(\d{3})(\d)/, "$1.$2") 
        .replace(/(\d{3})(\d{1,2})$/, "$1-$2") 
        .substring(0, 14); 
};

export function FormLogin() {
    const [dados, setDados] = useState({
        cpf: '',
        nome: '',
        email: '',
        senha: '',
    });

    const login = async (event) => {
        event.preventDefault();
        try{
            await loginUsuario(dados);
            alert('Login realizado com sucesso!');
        }
        catch(error){
            alert('Erro ao realizar login: ' + (error.response?.data?.message || error.message));
        }
    }

    return (
        <div className={styles.wrapper} style={{ backgroundImage: `url(${imagemCadastro})` }}>
            <div className={styles.overlay} />
            <div className={styles.container}>
                <p className={styles.brand}>CINEPLAY</p>
                <h2>Login</h2>
                <p className={styles.subtitle}>Preencha os dados abaixo para acessar sua conta.</p>
                <form onSubmit={login}>
                    <div className={styles.group}>
                        <input type="text" placeholder='CPF' name="cpf" value={dados.cpf} onChange={(e)=>setDados({...dados, cpf: formatarCPF(e.target.value)})} />
                    </div>
                    <div className={styles.group}>
                        <input type="text" placeholder='Nome' name="nome" value={dados.nome} onChange={(e)=>setDados({...dados, nome: e.target.value})} />
                    </div>
                    <div className={styles.group}>
                        <input type="email" placeholder='Email' name="email" value={dados.email} onChange={(e)=>setDados({...dados, email: e.target.value})} />
                    </div>
                    <div className={styles.group}>
                        <input type="password" placeholder='Senha' name="senha" value={dados.senha} onChange={(e)=>setDados({...dados, senha: e.target.value})} />
                    </div>
                    <button type='submit' className={styles.submitBtn}>Login</button>
                </form>
            </div>
        </div>
    );
}
