package saga.comparators;

import saga.entities.Compra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe comparadora de Compras, de acordo com o critério Data.
 *
 * @author Mariane Carvalho
 */
public class CompraComparatorData implements Comparator<Compra> {

    /**
     * Comparador de duas Compras, de acordo com o critério Data.
     * A comparação das datas é realizada
     * e caso sejam iguais as compras devem ser comparadas lexicograficamente
     * a partir das strings formadas pela concatenação de (nome_cliente, nome_fornecedor, desc_prod).
     *
     * @param compra1 Primeira compra.
     * @param compra2 Segunda compra.
     * @return Inteiro que representa se a primeira compra é lexicograficamente maior(>0), menor(<0) ou igual(0) à segunda compra.
     */
    public int compare(Compra compra1, Compra compra2) {
        Date data1 = new Date();
        Date data2 = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        try {
            data1 = formatoData.parse(compra1.getData());
            data2 = formatoData.parse(compra2.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (data1.equals(data2)) {
            String representacao1 = compra1.getNomeCliente()
                    + compra1.getNomeFornecedor() + compra1.getDescricaoProduto();
            String representacao2 = compra2.getNomeCliente()
                    + compra2.getNomeFornecedor() + compra2.getDescricaoProduto();

            return representacao1.compareTo(representacao2);
        }

        return data1.compareTo(data2);
    }
}
