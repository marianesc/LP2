package saga.comparators;

import saga.entities.Compra;

import java.util.Comparator;

/**
 * Classe comparadora de Compras, de acordo com o critério Cliente.
 *
 * @author Mariane Carvalho
 */
public class CompraComparatorCliente implements Comparator<Compra> {

    /**
     * Comparador de duas Compras, de acordo com o critério Cliente.
     * A comparação do nome dos clientes é realizada
     * e caso sejam iguais as compras devem ser comparadas lexicograficamente
     * a partir das strings formadas pela concatenação de (nome_fornecedor, desc_prod, data_compra).
     *
     * @param compra1 Primeira compra.
     * @param compra2 Segunda compra.
     * @return Inteiro que representa se a primeira compra é lexicograficamente maior(>0), menor(<0) ou igual(0) à segunda compra.
     */
    public int compare(Compra compra1, Compra compra2) {
        String nomeCliente1 = compra1.getNomeCliente();
        String nomeCliente2 = compra2.getNomeCliente();

        if (nomeCliente1.equals(nomeCliente2)) {
            String representacao1 = compra1.getNomeFornecedor()
                    + compra1.getDescricaoProduto() + compra1.getData();
            String representacao2 = compra2.getNomeFornecedor()
                    + compra2.getDescricaoProduto() + compra2.getData();

            return representacao1.compareTo(representacao2);
        }

        return nomeCliente1.compareTo(nomeCliente2);
    }
}