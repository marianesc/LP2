package saga.controllers;

import saga.Verificador;
import saga.entities.Fornecedor;
import saga.entities.Produto;
import saga.repositories.FornecedorRepositorio;

import java.util.List;

/**
 * Classe responsável pelo tratamento da lógica de produto.
 *
 * @author Mariane Carvalho
 */
public class ProdutoController {

    /**
     * Repositório de fornecedores.
     */
    private FornecedorRepositorio fornecedores;

    /**
     * Constroi um controller de produto recebendo o repositório de fornecedores.
     *
     * @param fornecedorRepositorio Repositório de fornecedores.
     */
    public ProdutoController(FornecedorRepositorio fornecedorRepositorio) {
        this.fornecedores = fornecedorRepositorio;
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
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(nome, "Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(descricao, "Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
        Verificador.verificaNumeroNegativo(preco, "Erro no cadastro de produto: preco invalido.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro no cadastro de produto: fornecedor nao existe.");
        Produto produto = new Produto(nome, descricao, preco);
        boolean cadastro = fornecedor.adicionaProduto(produto);
        if(!cadastro) throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
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
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(nome, "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(descricao, "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro na exibicao de produto: fornecedor nao existe.");
        Produto produto = fornecedor.consultaProduto(nome,descricao);
        Verificador.verificaNull(produto, "Erro na exibicao de produto: produto nao existe.");
        return produto.toString();
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
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro na exibicao de produto: fornecedor nao existe.");
        List<Produto> listaProdutos = fornecedor.consultaProdutos();
        String representacao = "";
        for(int i = 0; i < listaProdutos.size(); i++) {
            if(i != listaProdutos.size() - 1) {
                representacao += nomeFornecedor + " - " + listaProdutos.get(i).toString() + " | ";
            } else representacao += nomeFornecedor + " - " + listaProdutos.get(i).toString();
        }
        return representacao;
    }

    /**
     * Obtém a representação dos produtos de cada fornecedor.
     *
     * @return A representação dos produtos de cada fornecedor.
     */
    public String exibeProdutos() {
        String representacao = "";
        List<Fornecedor> listaFornecedores = this.fornecedores.consultaFornecedores();

        for(int i = 0; i < listaFornecedores.size(); i++) {

            List<Produto> listaProdutos = listaFornecedores.get(i).consultaProdutos();
            String nomeFornecedor = listaFornecedores.get(i).getNome();
            if(listaProdutos.size() == 0) {
                representacao += nomeFornecedor + " - | ";
                continue;
            }

            for(int j = 0; j < listaProdutos.size(); j++){
                if(i != listaFornecedores.size() - 1 || j != listaProdutos.size() - 1) {
                    representacao += nomeFornecedor + " - " + listaProdutos.get(j).toString() + " | ";
                }else representacao += nomeFornecedor + " - " + listaProdutos.get(j).toString();
            }
        }
        return representacao;
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
        Verificador.verificaStringNullVazia(nome, "Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(descricao, "Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
        Verificador.verificaNumeroNegativo(novoPreco, "Erro na edicao de produto: preco invalido.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro na edicao de produto: fornecedor nao existe.");
        Produto produto = fornecedor.consultaProduto(nome,descricao);
        Verificador.verificaNull(produto, "Erro na edicao de produto: produto nao existe.");
        produto.setPreco(novoPreco);
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
        Verificador.verificaStringNullVazia(nome, "Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
        Verificador.verificaStringNullVazia(descricao, "Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
        Verificador.verificaStringNullVazia(nomeFornecedor, "Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");

        Fornecedor fornecedor = this.fornecedores.consultaFornecedor(nomeFornecedor);
        Verificador.verificaNull(fornecedor, "Erro na remocao de produto: fornecedor nao existe.");
        Produto produto = fornecedor.consultaProduto(nome,descricao);
        Verificador.verificaNull(produto, "Erro na remocao de produto: produto nao existe.");
        fornecedor.removeProduto(produto);
    }
}
