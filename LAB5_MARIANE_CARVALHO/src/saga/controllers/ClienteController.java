package saga.controllers;

import saga.entities.Cliente;
import saga.repositories.ClienteRepositorio;
import saga.Verificador;

import java.util.Collections;
import java.util.List;

/**
 * Classe responsável pelo tratamento da lógica de cliente.
 *
 * @author Mariane Carvalho
 */
public class ClienteController {

    /**
     * Repositório de clientes.
     */
    private ClienteRepositorio clientes;

    /**
     * Constroi um controller de cliente recebendo seu repositório.
     *
     * @param clienteRepositorio Repositório de clientes.
     */
    public ClienteController(ClienteRepositorio clienteRepositorio) {
        this.clientes = clienteRepositorio;
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
    public String adicionaCliente(String cpf, String nome, String email, String localizacao) throws IllegalArgumentException {
        Verificador.verificaStringNullVazia(cpf,"Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(nome,"Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(email,"Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(localizacao,"Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
        Verificador.verificaValidadeCpfException(cpf, "Erro no cadastro do cliente: cpf invalido.");

        Cliente cliente = new Cliente(cpf, nome, email, localizacao);
        boolean status = this.clientes.adicionaCliente(cpf, cliente);
        if (!status) throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
        return cpf;
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
        Verificador.verificaStringNullVazia(cpf, "Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro na exibicao do cliente: cpf invalido.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro na exibicao do cliente: cliente nao existe.");
        return cliente.toString();
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
    public void editaCliente(String cpf, String atributo, String novoValor) throws IllegalArgumentException {
        Verificador.verificaStringNullVazia(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
        Verificador.verificaValidadeCpfException(cpf, "Erro na edicao do cliente: cpf invalido.");
        Verificador.verificaStringNullVazia(atributo, "Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
        if(atributo.equals("cpf")) throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser editado.");
        Verificador.verificaStringNullVazia(novoValor, "Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
        if(!Cliente.contemAtributo(atributo)) throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");

        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro na edicao do cliente: cliente nao existe.");
        if(atributo.equals("nome")) cliente.setNome(novoValor);
        else if(atributo.equals("email")) cliente.setEmail(novoValor);
        else cliente.setLocalizacao(novoValor);
    }

    /**
     * Obtém a representação de todos os clientes.
     *
     * @return Representação dos clientes.
     */
    public String exibeClientes() {
        List<Cliente> listaClientes = clientes.consultaClientes();
        Collections.sort(listaClientes);
        String representacao = "";
        for(int i = 0; i < listaClientes.size(); i++) {
            if(i != listaClientes.size() - 1) {
                representacao += listaClientes.get(i).toString() + " | ";
            } else representacao += listaClientes.get(i).toString();
        }
        return representacao;
    }

    /**
     * Remove um cliente do repositório.
     * Caso o cliente não exista é lançada uma exceção.
     *
     * @param cpf cpf do cliente.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public void removeCliente(String cpf) throws IllegalArgumentException {
        Verificador.verificaStringNullVazia(cpf, "Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
        
        Cliente cliente = this.clientes.consultaCliente(cpf);
        Verificador.verificaNull(cliente, "Erro na remocao do cliente: cliente nao existe.");
        this.clientes.removeCliente(cpf);
    }
}
