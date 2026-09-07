import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { cadastrarUsuario } from '../services/UsuarioService';
import { useLoading } from '../context/LoadingContext';
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

export function FormCadastro() {
    const [dados, setDados] = useState({
        cpf: '',
        nome: '',
        email: '',
        senha: '',
    });

    const navigate = useNavigate();
    const { startLoading, stopLoading } = useLoading();

    const cadastrar = async (event) => {
        event.preventDefault();
        try{
            startLoading();
            await cadastrarUsuario(dados);
            alert('Usuário cadastrado com sucesso!');
            navigate('/Login');
        }
        catch(error){
            alert('Erro ao cadastrar usuário: ' + (error.response?.data?.message || error.message));
        } finally {
            stopLoading();
        }
    }

    return (
        <div className={styles.wrapper} style={{ backgroundImage: `url(${imagemCadastro})` }}>
            <div className={styles.overlay} />
            <div className={styles.container}>
                <p className={styles.brand}>CINEPLAY</p>
                <h2>Cadastre-se</h2>
                <p className={styles.subtitle}>Preencha os dados abaixo para começar a avaliar.</p>
                <form onSubmit={cadastrar}>
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
                    <button type='submit' className={styles.submitBtn}>Cadastrar</button>
                </form>
            </div>
        </div>
    );
}
