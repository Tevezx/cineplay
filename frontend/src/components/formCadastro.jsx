export function FormCadastro() {
    const [formData, setFormData] = useState({
        cpf: '',
        nome: '',
        email: '',
        senha: '',
    });

    return (
        <form>
            <div>
                <label>CPF:</label>
                <input type="text" id="cpf" name="cpf" />
            </div>
            <div>
                <label>Nome:</label>
                <input type="text" id="nome" name="nome" />
            </div>
            <div>
                <label>Email:</label>
                <input type="email" id="email" name="email" />
            </div>
            <div>
                <label>Senha:</label>
                <input type="password" id="senha" name="senha" />
            </div>
            <button>Cadastrar</button>
        </form>
    );
}
