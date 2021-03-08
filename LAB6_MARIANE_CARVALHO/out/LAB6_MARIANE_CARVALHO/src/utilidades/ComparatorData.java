package utilidades;

import conta.Compra;
import java.util.Comparator;

/**
 * Classe comparadora de Compras, de acordo com o critério Data.
 *
 * @author Aluno de período anterior
 * @author Mariane Carvalho
 */
public class ComparatorData implements Comparator<Compra> {

	/**
	 * Compara duas compras através da data.
	 *
	 * @param o1 Primeira compra.
	 * @param o2 Segunda compra.
	 * @return Inteiro que representa se a primeira compra é lexicograficamente maior(>0), menor(<0) ou igual(0) à segunda compra.
	 */
	@Override
	public int compare(Compra o1, Compra o2) {
		String[] data1 = o1.getData().split("/");
		String[] data2 = o2.getData().split("/");

		if (Integer.valueOf(data1[2]) > Integer.valueOf(data2[2])) {
			return 1;
		}
		
		if (Integer.valueOf(data1[2]) < Integer.valueOf(data2[2])) {
			return -1;
		}

		if (Integer.valueOf(data1[1]) > Integer.valueOf(data2[1])) {
			return 1;
		}
		
		if (Integer.valueOf(data1[1]) < Integer.valueOf(data2[1])) {
			return -1;
		}

		if (Integer.valueOf(data1[0]) > Integer.valueOf(data2[0])) {
			return 1;
		}

		if (Integer.valueOf(data1[0]) < Integer.valueOf(data2[0])) {
			return -1;
		}

		return (o1.getCliente()+o1.getFornecedor()+o1.getDescricaoProduto()).compareTo(o2.getCliente()+o2.getFornecedor()+o2.getDescricaoProduto());

	}

}
