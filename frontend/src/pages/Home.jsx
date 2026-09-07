import { useNavigate } from 'react-router-dom';
import { Header } from '../components/Header';
import styles from '../styles/Home.module.css';
import imagemCadastro from '../assets/imagem_cadastro.jpg';

function Home() {
    const navigate = useNavigate();

    return (
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: true },
                { name: 'Cadastro', url: '/Cadastro', isActive: false },
                { name: 'Login', url: '/Login', isActive: false },
            ]} />

            <div className={styles.wrapper} style={{ backgroundImage: `url(${imagemCadastro})` }}>
                <div className={styles.overlay} />
                <section className={styles.container}>
                    <p className={styles.brand}>CINEPLAY</p>
                    <h1 className={styles.title}>Bem-vindo ao Cineplay</h1>
                    <p className={styles.subtitle}>Explore e avalie seus filmes favoritos!</p>
                    <button className={styles.primaryBtn} onClick={() => navigate('/Cadastro')}>
                        Cadastre-se
                    </button>
                    <p className={styles.helper}>Já possui uma conta?</p>
                    <button className={styles.secondaryBtn} onClick={() => navigate('/Login')}>
                        Faça login
                    </button>
                </section>
            </div>
        </div>
    );
}

export default Home;
