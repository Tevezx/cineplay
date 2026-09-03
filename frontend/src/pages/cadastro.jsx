import { Header } from '../components/header';

function Cadastro(){
    return(
        <div>
            <Header links={[
                { name: 'Home', url: '/' },
            ]} />
        </div>
    );
}

export default Cadastro;