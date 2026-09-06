import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Cadastro from './pages/Cadastro';
import Login from './pages/Login';
import CadastrarFilme from './pages/CadastrarFilme';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/Cadastro" replace />} />
        <Route path="/Cadastro" element={<Cadastro />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/CadastrarFilme" element={<CadastrarFilme />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
