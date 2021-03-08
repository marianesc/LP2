package saga.comparators;

import saga.entities.Compra;

import java.util.Comparator;

/**
 * Classe comparadora de Compras, de acordo com o critério Fornecedor.
 *
 * @author Mariane Carvalho
 */
public class CompraComparatorFornecedor implements Comparator<Compra> {

    /**
     * Comparador de duas Compras, de acordo com o critério Fornecedor.
     * A comparação do nome dos fornecedores é realizada
     * e caso sejam iguais as compras devem ser comparadas lexicograficamente
     * a partir das strings formadas pela concatenação de (nome_cliente, desc_prod, data_compra).
     *
     * @param compra1 Primeira compra.
     * @param compra2 Segunda compra.
     * @return Inteiro que representa se a primeira compra é lexicograficamente maior(>0), menor(<0) ou igual(0) à segunda compra.
     */
    public int compare(Compra compra1, Compra compra2) {
        String nomeFornecedor1 = compra1.getNomeFornecedor();
        String nomeFornecedor2 = compra2.getNomeFornecedor();

        if (nomeFornecedor1.equals(nomeFornecedor2)) {
            String representacao1 = compra1.getNomeCliente()
                    + compra1.getDescricaoProduto() + compra1.getData();
            String representacao2 = compra2.getNomeCliente()
                    + compra2.getDescricaoProduto() + compra2.getData();

            return representacao1.compareTo(representacao2);
        }

        return nomeFornecedor1.compareTo(nomeFornecedor2);
    }

}
