package saga.controllers;

import saga.Verificador;
import saga.entities.Cliente;
import saga.entities.Conta;
import saga.entities.Fornecedor;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

import java.util.List;

/**
 * Classe responsável pelo tratamento da lógica de conta.
 *
 * @author Mariane Carvalho
 */
public class ContaController {

    /**
     * Repositório de clientes.
     */
    private final ClienteRepositorio clientes;
    /**
     * Repositório de fornecedores.
     */
    private final FornecedorRepositorio fornecedores;

    /**
     * Constroi um controller de conta recebendo os repositórios de clientes e fornecedores.
     *
     * @param clienteRepositorio Repositório de clientes.
     * @param fornecedorRepositorio Repositório de fornecedores.
     */
    public ContaController(ClienteRepositorio clienteRepositorio, FornecedorRepositorio fornecedorRepositorio) {
        this.clientes = clienteRepositorio;
        this.fornecedores = fornecedorRepositorio;
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
        Verificador.verificaStringNullVazia(cpf, "Erro ao recuperar debito: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro ao recuperar debito: cpf invalido.");
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro ao recuperar debito: cliente nao existe.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro ao recuperar debito: fornecedor nao existe.");

        Conta conta = cliente.consultaConta(nomeFornecedor);
        Verificador.verificaNull(conta, "Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
        String formataPreco = String.format("%.2f", conta.getDebito()).replace(',', '.');
        return formataPreco;
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
        Verificador.verificaStringNullVazia(cpf, "Erro ao exibir conta do cliente: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro ao exibir conta do cliente: cpf invalido.");
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro ao exibir conta do cliente: cliente nao existe.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro ao exibir conta do cliente: fornecedor nao existe.");

        Conta conta = cliente.consultaConta(nomeFornecedor);
        Verificador.verificaNull(conta, "Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
        String representacao = "Cliente: " + cliente.getNome() + " | " + conta.toString();
        return representacao;
    }

    /**
     * Obtem a representação de todas as contas de um cliente.
     *
     * @param cpf cpf do cliente.
     * @return A representação de todas as contas de um cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public String exibeContasClientes(String cpf) throws IllegalArgumentException{
        Verificador.verificaStringNullVazia(cpf, "Erro ao exibir contas do cliente: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro ao exibir contas do cliente: cpf invalido.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro ao exibir contas do cliente: cliente nao existe.");
        List<Conta> contas = cliente.consultaContas();
        if(contas.size() == 0) throw new IllegalArgumentException("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.");

        String representacao = "Cliente: " + cliente.getNome() + " | ";
        for (int i = 0; i < contas.size(); i++) {
            if (i != contas.size() - 1) {
                representacao += contas.get(i).toString() + " | ";
            } else representacao += contas.get(i).toString();
        }
        return representacao;
    }
}
