import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Cadastro from './pages/Cadastro';
import Login from './pages/Login';
import CadastrarFilme from './pages/CadastrarFilme';
import { AvaliarFilmes } from './pages/AvaliarFilmes';
import { Loading } from './components/Loading';

function App() {
  return (
    <>
      <Loading />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Cadastro" element={<Cadastro />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/CadastrarFilme" element={<CadastrarFilme />} />
          <Route path="/AvaliarFilmes" element={<AvaliarFilmes />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
