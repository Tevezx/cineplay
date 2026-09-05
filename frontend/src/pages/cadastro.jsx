import { Header } from '../components/Header';
import { FormCadastro } from '../components/FormCadastro';

function Cadastro(){
    return(
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/Cadastro', isActive: true },
                { name: 'Login', url: '/Login', isActive: false },
            ]} />

            <FormCadastro />
        </div>  
    );
}

export default Cadastro;