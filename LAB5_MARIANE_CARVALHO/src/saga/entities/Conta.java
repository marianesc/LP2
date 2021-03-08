package saga.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma conta.
 *
 * @author Mariane Carvalho
 */
public class Conta {

    /**
     * Lista de compras na conta.
     */
    private List<Compra> compras;
    /**
     * Nome do fornecedor com quem há a conta.
     */
    private String nomeFornecedor;

    /**
     * Constroi uma conta a partir no nome do fornecedor com quem à terá.
     *
     * @param nomeFornecedor Nome do fornecedor.
     */
    public Conta(String nomeFornecedor) {
        this.compras = new ArrayList<>();
        this.nomeFornecedor = nomeFornecedor;
    }

    /**
     * Adiciona com compra à conta.
     *
     * @param compra Compra à ser adicionada.
     */
    public void adicionaCompra(Compra compra) {
        this.compras.add(compra);
    }

    /**
     * Obtém o somatório do valor de todas as compras na conta.
     *
     * @return O valor total de todas as compras.
     */
    public double getDebito() {
        double debito = 0;
        for(Compra compra : this.compras){
            debito += compra.getPreco();
        }
        return debito;
    }

    /**
     * Retorna a String que representa a conta.
     * A representação apresentará nome do fornecedor e as compras.
     *
     * @return A representação em String de conta.
     */
    public String toString() {
        String representacao = this.nomeFornecedor + " | ";
        for (int i = 0; i < this.compras.size(); i++) {
           if(i != this.compras.size() - 1) {
               representacao += this.compras.get(i).toString() + " | ";
           } else representacao += this.compras.get(i).toString();
        }
        return representacao;
    }

    /**
     * Obtém a lista de compras na conta.
     *
     * @return A lista de compras.
     */
    public List<Compra> consultaCompras() {
        return this.compras;
    }

    /**
     * Gera o hashCode do nome do fornecedor.
     *
     * @return O hashCode do nome do fornecedor.
     */
    public int hashCode() {
        return this.nomeFornecedor.hashCode();
    }

    /**
     * Confere se uma conta é igual à outra, comparando o nome do fornecedor.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return this.nomeFornecedor.equals(conta.nomeFornecedor);
    }

}
