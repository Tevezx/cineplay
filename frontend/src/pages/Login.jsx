import { Header } from '../components/Header';
import { FormLogin } from '../components/FormLogin';

function Login(){
    return(
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/Cadastro', isActive: false },
                { name: 'Login', url: '/Login', isActive: true },
            ]} />

            <FormLogin />
        </div>  
    );
}

export default Login;