import { Header } from "../components/Header";
import { FilmesCadastrados } from "../components/FilmesCadastrados";

function CadastrarFilme(){
    return (
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/Cadastro', isActive: false },
                { name: 'Login', url: '/Login', isActive: false },
                { name: 'Filmes', url: '/CadastrarFilme', isActive: true },
            ]} />
            <FilmesCadastrados />
        </div>
    );
}
export default CadastrarFilme;