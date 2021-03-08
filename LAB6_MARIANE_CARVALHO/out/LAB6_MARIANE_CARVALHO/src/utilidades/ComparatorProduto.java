package utilidades;

import java.util.Comparator;

import produto.Produto;

/**
 * Classe comparadora de Produto.
 *
 * @author Aluno de período anterior
 * @author Mariane Carvalho
 */
public class ComparatorProduto implements Comparator<Produto> {

	/**
	 * Compara dois produtos através do nome.
	 *
	 * @param p1 Primeiro produto.
	 * @param p2 Segundo produto.
	 * @return Inteiro que representa se o primeiro produto é lexicograficamente maior(>0), menor(<0) ou igual(0) ao segundo produto.
	 */
	@Override
	public int compare(Produto p1, Produto p2) {
		String nome1 = p1.getNome();
		String nome2 = p2.getNome();
		
		return nome1.compareTo(nome2);
	}
}

//Seu Olavo - Pastel de frango - Pastel de forno de frango - R$2,00 | Seu Olavo - Suco - Suco de maracuja (copo) - R$1,50 | Seu Olavo - X-burguer - Hamburguer de carne com queijo e calabresa - R$4,50 | Seu Olavo - X-burguer + refrigerante - X-burguer com refri (lata) - R$6,00 | Seu Olavo - X-burguer + suco - X-burguer com suco de maracuja - R$4,80
//Seu Olavo - Pastel de frango - Pastel de forno de frango - R$2,00 | Seu Olavo - Suco - Suco de maracuja (copo) - R$1,50 | Seu Olavo - X-burguer + refrigerante - X-burguer com refri (lata) - R$6,00 | Seu Olavo - X-burguer + suco - X-burguer com suco de maracuja - R$4,80 | Seu Olavo - X-burguer - Hamburguer de carne com queijo e calabresa - R$4,50
