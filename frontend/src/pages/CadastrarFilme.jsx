import { Header } from "../components/Header";

function CadastrarFilme(){
    return (
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/Cadastro', isActive: false },
                { name: 'Login', url: '/Login', isActive: false },
                { name: 'Cadastrar Filme', url: '/CadastrarFilme', isActive: true },
            ]} />
        </div>
    );
}
export default CadastrarFilme;