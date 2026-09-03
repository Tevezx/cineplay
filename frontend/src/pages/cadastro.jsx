import { Header } from '../components/header';

function Cadastro(){
    return(
        <div>
            <Header links={[
                { name: 'Cadastro', url: '/cadastro', isActive: true },
                { name: 'Login', url: '/login', isActive: false },
            ]} />
        </div>
    );
}

export default Cadastro;