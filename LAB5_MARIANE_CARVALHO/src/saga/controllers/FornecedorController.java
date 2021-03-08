package saga.controllers;

import saga.Verificador;
import saga.entities.Fornecedor;
import saga.repositories.FornecedorRepositorio;

import java.util.List;

/**
 * Classe responsável pelo tratamento da lógica de fornecedor.
 *
 * @author Mariane Carvalho
 */
public class FornecedorController {

    /**
     * Repositório de fornecedores.
     */
    private FornecedorRepositorio fornecedores;

    /**
     * Constroi um controller de fornecedor recebendo seu repositório.
     *
     * @param fornecedorRepositorio Repositório de fornecedores.
     */
    public FornecedorController(FornecedorRepositorio fornecedorRepositorio) {
        this.fornecedores = fornecedorRepositorio;
    }

    /**
     * Adiciona um fornecedor ao repositório.
     * Caso o fornecedor já exista é lançada uma exceção.
     *
     * @param nome Nome do fornecedor.
     * @param email Email do fornecedor.
     * @param telefone Telefone do fornecedor.
     * @return Nome do fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String adicionaFornecedor(String nome, String email, String telefone) throws IllegalArgumentException{
        Verificador.verificaStringNullVazia(nome,"Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(email,"Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(telefone,"Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");

        Fornecedor fornecedor = new Fornecedor(nome, email, telefone);
        boolean status = this.fornecedores.adicionaFornecedor(nome, fornecedor);
        if (!status) throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
        return nome;
    }

    /**
     * Obtém a representação de um fornecedor.
     * Caso o fornecedor não exista é lançada uma exceção.
     *
     * @param nome Nome do fornecedor.
     * @return A representação de um fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeFornecedor(String nome) throws IllegalArgumentException{
        Verificador.verificaStringNullVazia(nome, "Erro na exibicao do fornecedor: nome nao pode ser vazio ou nulo.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nome);
        Verificador.verificaNull(fornecedor, "Erro na exibicao do fornecedor: fornecedor nao existe.");
        return fornecedor.toString();
    }

    /**
     * Edita um atributo de um fornecedor.
     * Caso o fornecedor não exista é lançada uma exceção.
     *
     * @param nome Nome do forncedor.
     * @param atributo Atributo a ser editado.
     * @param novoValor Novo valor do atributo.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void editaFornecedor(String nome, String atributo, String novoValor) throws IllegalArgumentException{
        Verificador.verificaStringNullVazia(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(atributo, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
        if(atributo.equals("nome")) throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
        Verificador.verificaStringNullVazia(novoValor, "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
        if(!Fornecedor.contemAtributo(atributo)) throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nome);
        Verificador.verificaNull(fornecedor, "Erro na edicao do fornecedor: fornecedor nao existe.");
        if(atributo.equals("email")) fornecedor.setEmail(novoValor);
        else fornecedor.setTelefone(novoValor);
    }

    /**
     * Obtém a representação de todos os fornecedores.
     *
     * @return Representação dos fornecedores.
     */
    public String exibeFornecedores() {
        List<Fornecedor> listaFornecedores = fornecedores.consultaFornecedores();
        String representacao = "";
        for(int i = 0; i < listaFornecedores.size(); i++) {
            if(i != listaFornecedores.size() - 1) {
                representacao += listaFornecedores.get(i).toString() + " | ";
            } else representacao += listaFornecedores.get(i).toString();
        }
        return representacao;
    }

    /**
     * Remove um fornecedor do repositório.
     * Caso o fornecedor não exista é lançada uma exceção.
     *
     * @param nome Nome do fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void removeFornecedor(String nome) throws IllegalArgumentException {
        Verificador.verificaStringNullVazia(nome, "Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nome);
        Verificador.verificaNull(fornecedor, "Erro na remocao do fornecedor: fornecedor nao existe.");
        this.fornecedores.removeFornecedor(nome);
    }
}
