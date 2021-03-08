package saga;

import easyaccept.EasyAccept;
import saga.controllers.*;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

/**
 * Classe que provê uma interface simplificada para todo um sistema.
 *
 * @author Mariane Carvalho
 */
public class Facade {

    /**
     * Controller de cliente.
     */
    private ClienteController clienteController;
    /**
     * Controller de fornecedor.
     */
    private FornecedorController fornecedorController;
    /**
     * Controller de produto.
     */
    private ProdutoController produtoController;
    /**
     * Controller de compra.
     */
    private CompraController compraController;
    /**
     * Controller de conta.
     */
    private ContaController contaController;

    /**
     * Main para executar os testes de aceitação.
     *
     * @param args a facade e o scrip de testes.
     */
    public static void main(String[] args) {
        args = new String[] {"saga.Facade",
                "acceptance_test/use_case_1.txt", "acceptance_test/use_case_2.txt",
                "acceptance_test/use_case_3.txt", "acceptance_test/use_case_4.txt",
                "acceptance_test/use_case_5.txt", "acceptance_test/use_case_6.txt",
        };
        EasyAccept.main(args);
    }

    /**
     * Contrutor que inicializa os atributos.
     */
    public Facade() {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();

        this.clienteController = new ClienteController(clienteRepositorio);
        this.fornecedorController = new FornecedorController(fornecedorRepositorio);
        this.produtoController = new ProdutoController(fornecedorRepositorio);
        this.compraController = new CompraController(clienteRepositorio, fornecedorRepositorio);
        this.contaController = new ContaController(clienteRepositorio, fornecedorRepositorio);
    }

    /**
     * Adiciona um cliente ao repositório.
     * Caso o cliente já exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @param nome Nome do cliente.
     * @param email Email do cliente.
     * @param localizacao Local onde o cliente trabalha.
     * @return Retorna o cpf do cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String adicionaCliente(String cpf, String nome, String email, String localizacao) throws IllegalArgumentException{
        return this.clienteController.adicionaCliente(cpf, nome, email, localizacao);
    }

    /**
     * Obtém a representação de um cliente.
     * Caso o cliente não exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @return A representação de um cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeCliente(String cpf) throws IllegalArgumentException {
        return this.clienteController.exibeCliente(cpf);
    }

    /**
     * Edita um atributo de um cliente.
     * Caso o cliente não exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @param atributo Atributo a ser editado.
     * @param novoValor Novo valor do atributo.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void editaCliente(String cpf, String atributo, String novoValor) throws IllegalArgumentException{
        this.clienteController.editaCliente(cpf, atributo, novoValor);
    }

    /**
     * Obtém a representação de todos os clientes.
     *
     * @return Representação dos clientes.
     */
    public String exibeClientes() {
        return this.clienteController.exibeClientes();
    }

    /**
     * Remove um cliente do repositório.
     * Caso o cliente não exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void removeCliente(String cpf) throws IllegalArgumentException{
        this.clienteController.removeCliente(cpf);
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
        return this.fornecedorController.adicionaFornecedor(nome, email, telefone);
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
        return this.fornecedorController.exibeFornecedor(nome);
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
        this.fornecedorController.editaFornecedor(nome, atributo, novoValor);
    }

    /**
     * Obtém a representação de todos os fornecedores.
     *
     * @return Representação dos fornecedores.
     */
    public String exibeFornecedores() {
        return this.fornecedorController.exibeFornecedores();
    }

    /**
     * Remove um fornecedor do repositório.
     * Caso o fornecedor não exista é lançada uma exceção.
     *
     * @param nome Nome do fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void removeFornecedor(String nome) throws IllegalArgumentException{
        this.fornecedorController.removeFornecedor(nome);
    }

    /**
     * Adiciona um produto ao repositório de um fornecedor.
     * Caso o fornecedor não exista ou o produto já exista é lançada uma exceção.
     *
     * @param nomeFornecedor Nome do fornecedor.
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param preco Preço do produto.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void adicionaProduto(String nomeFornecedor, String nome, String descricao, double preco) throws IllegalArgumentException{
        this.produtoController.adicionaProduto(nomeFornecedor, nome, descricao, preco);
    }

    /**
     * Obtém a representação de um produto.
     * Caso o fornecedor ou o produto não exista é lançada uma exceção.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param nomeFornecedor Nome do fornecedor.
     * @return A representação de um produto.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeProduto(String nome, String descricao, String nomeFornecedor) throws IllegalArgumentException{
        return this.produtoController.exibeProduto(nome, descricao, nomeFornecedor);
    }

    /**
     * Obtém a representação de todos os produtos de um fornecedor.
     * Caso o fornecedor não exista é lançada uma exceção.
     *
     * @param nomeFornecedor Nome do fornecedor.
     * @return A representação dos produtos de um fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeProdutosFornecedor(String nomeFornecedor) throws IllegalArgumentException{
        return this.produtoController.exibeProdutosFornecedor(nomeFornecedor);
    }

    /**
     * Obtém a representação dos produtos de cada fornecedor.
     *
     * @return A representação dos produtos de cada fornecedor.
     */
    public String exibeProdutos() {
        return this.produtoController.exibeProdutos();
    }

    /**
     * Edita o preço de um produto.
     * Caso o fornecedor ou o produto não exista é lançada uma exceção.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param nomeFornecedor Nome do fornecedor.
     * @param novoPreco Novo valor do preço.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void editaProduto(String nome, String descricao, String nomeFornecedor, double novoPreco) throws IllegalArgumentException{
        this.produtoController.editaProduto(nome, descricao, nomeFornecedor, novoPreco);
    }

    /**
     * Remove um produto do repositório de um fornecedor.
     * Caso o fornecedor ou o produto não exista é lançada uma exceção.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param nomeFornecedor Nome do fornecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void removeProduto(String nome, String descricao, String nomeFornecedor) throws IllegalArgumentException{
        this.produtoController.removeProduto(nome, descricao,nomeFornecedor);
    }

    /**
     * Adiciona uma compra à conta de um cliente com um fornecedor.
     * Caso o cliente, o fornecedor ou o produto não exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @param nomeFornecedor Nome do fornecedor.
     * @param data Data da compra.
     * @param nomeProd Nome do produto.
     * @param descricaoProd Descrição do produto.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void adicionaCompra(String cpf, String nomeFornecedor, String data, String nomeProd, String descricaoProd) throws IllegalArgumentException{
        this.compraController.adicionaCompra(cpf, nomeFornecedor, data, nomeProd, descricaoProd);
    }

    /**
     * Obtém a totalidade da conta de um cliente com um fornecedor.
     * Caso o cliente ou o fornecedor não exista, ou o cliente não tenha uma conta com o fornecedor é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @param nomeFornecedor Nome do fornecedor.
     * @return O valor da totalidade da conta.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String getDebito(String cpf, String nomeFornecedor) throws IllegalArgumentException{
        return this.contaController.getDebito(cpf, nomeFornecedor);
    }

    /**
     * Obtém a representação da conta de um cliente com um fonecedor.
     * Caso o cliente ou o fornecedor não exista, ou o cliente não tenha uma conta com o fornecedor é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @param nomeFornecedor Nome do fornecedor.
     * @return A representação da conta de um cliente com um fonecedor.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeContas(String cpf, String nomeFornecedor) throws IllegalArgumentException{
        return this.contaController.exibeContas(cpf, nomeFornecedor);
    }

    /**
     * Obtem a representação de todas as contas de um cliente.
     *
     * @param cpf cpf do cliente.
     * @return A representação de todas as contas de um cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeContasClientes(String cpf) throws IllegalArgumentException{
        return this.contaController.exibeContasClientes(cpf);
    }

    /**
     * Define o critério pelo qual deve haver a ordenação.
     * Caso o critério não seja oferecido pelo sistema é lançada uma exceção.
     *
     * @param criterio Critério de ordenação.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void ordenaPor(String criterio) throws IllegalArgumentException {
        this.compraController.setCriterioOrdenacao(criterio);
    }

    /**
     * Lista as compras de todos os clientes ordenadas de acordo com o critério escolhido.
     *
     * @return Representação ordenada da lista de compras de todos os clientes.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String listarCompras() throws IllegalArgumentException {
        return this.compraController.listarCompras();
    }
}
