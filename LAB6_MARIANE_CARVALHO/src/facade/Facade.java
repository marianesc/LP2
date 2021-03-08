package facade;

/**
 * Fachada do sistema.
 */
public class Facade {

	/**
	 * Instância do controlador central.
	 */
	private ControlerCentral cc;

	/**
	 * Construtor que inicializa o atributo.
	 */
	public Facade() {
		this.cc = new ControlerCentral();
	}

	/**
	 * Adiciona um cliente ao controlador de cliente.
	 *
	 * @param cpf 	      Cpf do cliente
	 * @param nome        Nome do cliente
	 * @param email       Email do cliente
	 * @param localizacao Localizacao do cliente
	 * @return CPF do cliente se ele for cadastrado com sucesso
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) {
		return this.cc.adicionaCliente(cpf, nome, email, localizacao);
	}

	/**
	 * Retorna a representacao textual de um cliente dado o seu cpf
	 *
	 * @param cpf Cpf do cliente que se deseja obter a representacao textual
	 * @return Representacao textual do cliente
	 */
	public String exibeCliente(String cpf) {
		return this.cc.exibeCliente(cpf);
	}

	/**
	 * Lista todos os clientes cadastrados no controlador, em ordem alfabetica do
	 * nome
	 *
	 * @return String que é a lista das representacoes textuais de todos os clientes
	 *         cadastrados no controlador
	 */
	public String exibeClientes() {
		return this.cc.exibeClientes();
	}

	/**
	 * Edita o cadastro de um cliente dado o cpf, o atributo que se deseja mudar e o
	 * novo valor que sera atribuido a ele.
	 *
	 * @param cpf       CPF do cliente
	 * @param atributo  Atributo a ser modificado
	 * @param novoValor Novo valor do atributo
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) {
		this.cc.editaCliente(cpf, atributo, novoValor);
	}

	/**
	 * Remove um cliente do cadastro dado seu cpf.
	 *
	 * @param cpf Cpf do cliente a ser removido.
	 */
	public void removeCliente(String cpf) {
		this.cc.removeCliente(cpf);
	}

	/**
	 * Adiciona um fornecedor ao sistema dado seu nome, telefone e email
	 *
	 * @param nome     Nome do fornecedor
	 * @param email    Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 * @return Nome do fornecedor cadastrado
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		return this.cc.adicionaFornecedor(nome, email, telefone);
	}

	/**
	 * Retorna a representação textual de um fornecedor dado o seu nome
	 *
	 * @param nome Nome do fornecedor que se deseja obter a representação textual
	 * @return String que é a representação textual do fornecedor
	 */
	public String exibeFornecedor(String nome) {
		return this.cc.exibeFornecedor(nome);
	}

	/**
	 * Retorna uma lista dos fornecedores em String.
	 *
	 * @return String que é a lista de todos os fornecedores cadastrados no sistema.
	 */
	public String exibeFornecedores() {
		return this.cc.exibeFornecedores();
	}

	/**
	 * Edita um fornecedor dado o nome do fornecedor, o atributo que se deseja
	 * editar e o novo valor.
	 *
	 * @param nome      Nome dor fornecedor que se deseja editar.
	 * @param atributo  Nome do atributo que se deseja editar.
	 * @param novoValor Novo valor que se deseja atribuir ao atributo.
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		this.cc.editaFornecedor(nome, atributo, novoValor);
	}

	/**
	 * Remove um fornecedor do cadastro dado o nome do fornecedor
	 *
	 * @param nome Nome do fornecedor que se deseja remover.
	 */
	public void removeFornecedor(String nome) {
		this.cc.removeFornecedor(nome);
	}

	/**
	 * Adiciona um produto para um fornecedor.
	 *
	 * @param fornecedor Nome do fornecedor que se deseja adicionar o produto
	 * @param nome       Nome do produto
	 * @param descricao  Desricao do produto
	 * @param preco      preco do produto
	 */
	public void adicionaProduto(String fornecedor, String nome, String descricao, double preco) {
		this.cc.adicionaProduto(fornecedor, nome, descricao, preco);
	}

	/**
	 * Exibe um produto de um fornecedor, dado o nome do fornecedor, o nome e a
	 * descrição do produto
	 *
	 * @param nome       Nome do produto
	 * @param descricao  Descrição do porduto
	 * @param fornecedor Nome do fornecedor
	 * @return String que é a representação textual do produto
	 */
	public String exibeProduto(String fornecedor, String nome, String descricao) {
		return this.cc.exibeProduto(fornecedor, nome, descricao);
	}

	/**
	 * Lista todos os produtos de um fornecedor em ordem alfabética
	 *
	 * @param fornecedor Nome do fornecedor
	 * @return String que é a lista de todos os produtos que esse fornecedor oferece
	 *         ordenados em ordem alfabética do nome do produto
	 */
	public String exibeProdutosFornecedor(String fornecedor) {
		return this.cc.exibeProdutosFornecedor(fornecedor);
	}

	/**
	 * Lista todos os produtos de todos os forncedores em ordem alfabética dos
	 * fornecedores e dos produtos
	 *
	 * @return String que é uma lista de todos os produtos de todos os fornecedores
	 *         em ordem alfabética do nome dos forncedores e dos produtos
	 */
	public String exibeProdutos() {
		return this.cc.exibeProdutos();
	}

	/**
	 * Edita o preco de um produto de um fornecedor dado o nome do fornecedor, nome
	 * e descricao do produto e o novo valor
	 *
	 * @param nome       Nome do produto
	 * @param descricao  Descricao do produto
	 * @param fornecedor Nome do fornecedor
	 * @param novoPreco  Novo preco do produto
	 */
	public void editaProduto(String fornecedor, String nome, String descricao, double novoPreco) {
		this.cc.editaProduto(fornecedor, nome, descricao, novoPreco);
	}

	/**
	 * Remove o produto de um fornecedor dado o nome do fornecedor e o nome e
	 * descricao do produto.
	 *
	 * @param nome       Nome do produto
	 * @param descricao  Decricao do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public void removeProduto(String fornecedor, String nome, String descricao) {
		this.cc.removeProduto(fornecedor, nome, descricao);
	}

	/**
	 * Adiciona uma compra de um produto feita por um cliente em um dado fornecedor,
	 * dado o cpf do cliente, nome do fornecedor, data da compra, nome, descrição e
	 * preço do produto.
	 *
	 * @param cpf              Cpf do cliente que realizou a compra
	 * @param fornecedor       Nome do fornecedor de quem o cliente comprou o
	 *                         produto
	 * @param data             Data da compra
	 * @param nomeProduto      Nome do produto comprado
	 * @param descricaoProduto Descrição do produto comprado.
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nomeProduto,
			String descricaoProduto) {
		this.cc.adicionaCompra(cpf, fornecedor, data, nomeProduto, descricaoProduto);
	}

	/**
	 * Pega o débito que um cliente tem para um dado fornecedor dado o cpf do
	 * cliente e o nome do fornecedor. Retorna um double que é o débito total
	 * daquela conta, caso exista.
	 *
	 * @param cpf        CPF do cliente
	 * @param fornecedor Fornecedor que o cliente tem a conta e que se deseja saber
	 *                   o débito total.
	 * @return Double que é o valor total das compras realizadas pelo cliente
	 *         naquele dado fornecedor.
	 */
	public double getDebito(String cpf, String fornecedor) {
		return this.cc.getDebito(cpf, fornecedor);
	}

	/**
	 * Exibe uma conta de um cliente para um dado fornecedor.
	 *
	 * @param cpf        Cpf do cliente
	 * @param fornecedor Fornecedor no qual o cliente tem uma conta.
	 * @return Representação textual da conta do cliente para o dado fornecedor
	 */
	public String exibeContas(String cpf, String fornecedor) {
		return this.cc.exibeContas(cpf, fornecedor);
	}

	/**
	 * Retorna uma representação String de todas as contas de um dado cliente
	 * Organizadas em oredem alfabética do nome dos fornecedores
	 *
	 * @param cpf CPF do cliente que se deseja ver as contas.
	 * @return Representação textual de todas as contas de um cliente
	 */
	public String exibeContasClientes(String cpf) {
		return this.cc.exibeContasClientes(cpf);
	}

	/**
	 * Define o critério de ordenação das compras do sistema, por ordem alfabética
	 * do nome do cliente, do nome do fornecedor, ou pela data das compras
	 *
	 * @param criterio Critério de ordenação
	 */
	public void ordenaPor(String criterio) {
		this.cc.ordenaPor(criterio);
	}

	/**
	 * Retorna a representação String de todas as compras realizadas no sistema
	 * ordenadas pelo critério pré definido anteriormente
	 *
	 * @return Representação String de todas as compras realizadas no sistema.
	 */
	public String listarCompras() {
		return this.cc.listarCompras();
	}

	/**
	 * Adiciona um combo para um fornecedor.
	 *
	 * @param fornecedor Nome do fornecedor que se deseja adicionar o combo.
	 * @param nome Nome do combo.
	 * @param descricao Descrição do combo.
	 * @param fator Fator de promoção do combo.
	 * @param produtos Nome e descrição dos produtos que compõe o combo.
	 */
	public void adicionaCombo(String fornecedor, String nome, String descricao,  double fator, String produtos) {
		this.cc.adicionaCombo(fornecedor, nome, descricao, fator, produtos);
	}

	/**
	 * Edita o fator de um combo de um fornecedor dado o nome e descricao do combo,
	 * nome do fornecedor e novo valor do fator.
	 *
	 * @param nome Nome do combo.
	 * @param descricao Descrição do combo.
	 * @param fornecedor Nome do fornecedor.
	 * @param novoFator Novo fator do combo.
	 */
	public void editaCombo(String nome, String descricao, String fornecedor, double novoFator) {
		this.cc.editaCombo(nome, descricao, fornecedor, novoFator);
	}
}
