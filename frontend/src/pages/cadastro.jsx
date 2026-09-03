import { Header } from '../components/header';

function Cadastro(){
    return(
        <div>
            <Header links={[
                { name: 'Cadastro', url: '/cadastro' },
                { name: 'Login', url: '/login' },
            ]} />
        </div>
    );
}

export default Cadastro;