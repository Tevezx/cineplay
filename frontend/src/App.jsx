import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
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
          <Route path="/" element={<Navigate to="/Cadastro" replace />} />
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
