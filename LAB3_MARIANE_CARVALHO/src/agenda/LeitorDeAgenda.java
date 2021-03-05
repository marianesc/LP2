package agenda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lógica para ler de arquivos csv os campos necessários e povoar uma agenda. 
 * 
 * @author nazarenoandrade
 * @author Mariane Carvalho
 */
public class LeitorDeAgenda {

	/**
	 * Lê contatos de um arquivo CSV e os coloca em uma agenda.
	 *
	 * @param arquivoContatos Caminho para arquivo contendo contatos.
	 * @param agenda A agenda a manipular.
	 * @return O número de contatos adicionados à agenda.
	 * @throws IOException Caso não tenhamos permissão de ler o arquivo.
	 * @throws FileNotFoundException Caso o arquivo não exista.
	 */
	public int carregaContatos(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		int carregados = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(arquivoContatos))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				carregados += 1;
				if (carregados == 1) {
					// pulamos a primeira linha, o cabeçalho
					continue;
				}
				String[] campos = linha.split(",");
				processaLinhaCsvContatos(campos, agenda);
			}
		}
		
		return carregados - 1;
	}

	
	/**
	 * Coloca na agenda os campos de uma linha do arquivo de agenda inicial.
	 * 
	 * @param campos As informações lidas do csv. 
	 * @param agenda A agenda a manipular. 
	 */
	private void processaLinhaCsvContatos(String[] campos, Agenda agenda) {
		retiraIndesejados(campos);
		int posicao = Integer.parseInt(campos[0]);
		String nome = campos[1];
		String sobrenome = campos[2];
		String telefone1 = campos[3];
		String telefone2 = campos[4];
		String telefone3 = campos[5];
		int prioritario = Integer.parseInt(campos[6]);
		int whatsapp = Integer.parseInt(campos[7]);
		Contato contato = new Contato(nome, sobrenome,telefone1,telefone2,telefone3,prioritario,whatsapp);
		agenda.cadastraContato(posicao, contato);
	}

	/**
	 * Remove os espaços em branco no ínicio e no final dos campos
	 * e as Strings "", que ficaram ao fazer split() no espaço vazio.
	 *
	 * @param campos As informações lidas do csv.
	 */
	private void retiraIndesejados(String[] campos) {
		for (int i = 0; i < campos.length; i++) {
			campos[i] = campos[i].trim();
			if (campos[i].equals("\"\"")) campos[i] = "";
		}
	}

}
