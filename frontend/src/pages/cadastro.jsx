import { Header } from '../components/header';
import { FormCadastro } from '../components/formCadastro';

function Cadastro(){
    return(
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/cadastro', isActive: true },
                { name: 'Login', url: '/login', isActive: false },
            ]} />

            <FormCadastro />
        </div>  
    );
}

export default Cadastro;