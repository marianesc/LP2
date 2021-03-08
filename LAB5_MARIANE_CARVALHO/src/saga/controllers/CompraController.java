package saga.controllers;

import saga.Verificador;
import saga.comparators.CompraComparatorCliente;
import saga.comparators.CompraComparatorData;
import saga.comparators.CompraComparatorFornecedor;
import saga.entities.Cliente;
import saga.entities.Compra;
import saga.entities.Fornecedor;
import saga.entities.Produto;
import saga.entities.Conta;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo tratamento da lógica de compra.
 *
 * @author Mariane Carvalho
 */
public class CompraController {

    /**
     * Repositório de clientes.
     */
    private final ClienteRepositorio clientes;
    /**
     * Repositório de fornecedores.
     */
    private final FornecedorRepositorio fornecedores;
    /**
     * Critério de ordenação de lista.
     */
    private String criterioOrdenacao;

    /**
     * Constroi um controller de compra recebendo os repositórios de clientes e fornecedores.
     *
     * @param clienteRepositorio Repositório de clientes.
     * @param fornecedorRepositorio Repositório de fornecedores.
     */
    public CompraController(ClienteRepositorio clienteRepositorio, FornecedorRepositorio fornecedorRepositorio) {
        this.clientes = clienteRepositorio;
        this.fornecedores = fornecedorRepositorio;
        this.criterioOrdenacao = null;
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
        Verificador.verificaStringNullVazia(cpf, "Erro ao cadastrar compra: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro ao cadastrar compra: cpf invalido.");
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(data, "Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
        Verificador.verificaData(data, "Erro ao cadastrar compra: data invalida.");
        Verificador.verificaStringNullVazia(nomeProd, "Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(descricaoProd, "Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro ao cadastrar compra: cliente nao existe.");
        String nomeCliente = cliente.getNome();

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro ao cadastrar compra: fornecedor nao existe.");

        Produto produto = fornecedor.consultaProduto(nomeProd,descricaoProd);
        Verificador.verificaNull(produto, "Erro ao cadastrar compra: produto nao existe.");
        double preco = produto.getPreco();

        Compra compra = new Compra(nomeProd, descricaoProd, preco, data, nomeCliente, nomeFornecedor);
        cliente.adicionaNaConta(nomeFornecedor, compra);

    }

    /**
     * Define o critério pelo qual deve haver a ordenação.
     * Caso o critério não seja oferecido pelo sistema é lançada uma exceção.
     *
     * @param criterioOrdenacao Critério de ordenação.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void setCriterioOrdenacao(String criterioOrdenacao) throws IllegalArgumentException {
        Verificador.verificaStringNullVazia(criterioOrdenacao, "Erro na listagem de compras: criterio nao pode ser vazio ou nulo.");

        criterioOrdenacao = criterioOrdenacao.toLowerCase();
        if (!criterioOrdenacao.equals("cliente") &&
                !criterioOrdenacao.equals("fornecedor") &&
                !criterioOrdenacao.equals("data")) {
            throw new IllegalArgumentException("Erro na listagem de compras: criterio nao oferecido pelo sistema.");
        }

        this.criterioOrdenacao = criterioOrdenacao;
    }

    /**
     * Define através do critério de ordenação que comparator será usado para a ordenação.
     *
     * @param compras Lista de compras de todos os os clientes.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    private void ordenarCompras(List<Compra> compras) throws IllegalArgumentException {
        Verificador.verificaNull(this.criterioOrdenacao, "Erro na listagem de compras: criterio ainda nao definido pelo sistema.");

        switch (this.criterioOrdenacao) {
            case "cliente":
                compras.sort(new CompraComparatorCliente());
                break;
            case "fornecedor":
                compras.sort(new CompraComparatorFornecedor());
                break;
            case "data":
                compras.sort(new CompraComparatorData());
                break;
        }
    }

    /**
     * Lista as compras de todos os clientes ordenadas de acordo com o critério escolhido.
     *
     * @return Representação ordenada da lista de compras de todos os clientes.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String listarCompras() throws IllegalArgumentException {
        List<Compra> todasCompras = new ArrayList<>();

        List<Cliente> clientes = this.clientes.consultaClientes();
        for (Cliente cliente : clientes) {
            List<Conta> contas = cliente.consultaContas();
            for (Conta conta : contas) {
                List<Compra> compras = conta.consultaCompras();
                todasCompras.addAll(compras);
            }
        }

        this.ordenarCompras(todasCompras);

        StringBuilder listagem = new StringBuilder();
        for (int i = 0; i < todasCompras.size(); i++) {
            Compra compraAtual = todasCompras.get(i);
            String representacao = compraAtual.getRepresentacao(this.criterioOrdenacao);
            listagem.append(representacao);

            if (i != todasCompras.size() - 1) {
                listagem.append(" | ");
            }
        }

        return listagem.toString();
    }
}
