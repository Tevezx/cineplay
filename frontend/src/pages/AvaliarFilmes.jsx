import { Header } from "../components/Header";
import { FilmesAvaliacao } from "../components/FilmesAvaliacao";

export function AvaliarFilmes(){
    return (
        <div>
            <Header links={[
                { name: 'Home', url: '/', isActive: false },
                { name: 'Cadastro', url: '/Cadastro', isActive: false },
                { name: 'Login', url: '/Login', isActive: false },
                { name: 'Avaliação', url: '/AvaliarFilmes', isActive: true },
            ]}/>
            <FilmesAvaliacao />
        </div>
    );
}