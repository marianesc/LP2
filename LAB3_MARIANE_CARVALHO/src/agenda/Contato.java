package agenda;

/**
 * Representação de um contato.
 *
 * @author Mariane Carvalho
 */
public class Contato {

    /**
     * Nome do contato.
     */
    private String nome;
    /**
     * Sobrenome do contato.
     */
    private String sobrenome;
    /**
     * Que telefone o contato usa como prioritário.
     */
    private int prioritario;
    /**
     * Que telefone o contato usa como whatsapp.
     */
    private int whatsapp;
    /**
     * Array dos telefones do contato.
     */
    private String[] telefones;

    /**
     * Constroi um contato a partir de seu nome, sobrenome, 3 telefones,
     * qual dos telefones é usado como prioritário, e qual é usado para o whatsapp.
     *
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone1 Telefone 1 do contato.
     * @param telefone2 Telefone 2 do contato.
     * @param telefone3 Telefone 3 do contato
     * @param telefonePrioritario Qual dos telefones o contato usa como prioritário.
     * @param whatsapp Qual dos telefones o contato usa como whatsapp.
     */
    public Contato(String nome, String sobrenome, String telefone1, String telefone2, String telefone3, int telefonePrioritario, int whatsapp) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = new String[3];
        this.telefones[0] = telefone1;
        this.telefones[1] = telefone2;
        this.telefones[2] = telefone3;
        this.prioritario = telefonePrioritario;
        this.whatsapp = whatsapp;
    }

    /**
     * Confere se um contato é igual ao outro, comparando nome e sobrenome.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return this.getNomeCompleto().equals(contato.getNomeCompleto());
    }

    /**
     * Gera o hashCode do nome completo do contato.
     *
     * @return Retorna o hashCode do nome completo do contato.
     */
    public int hashCode() {
        return this.getNomeCompleto().hashCode();
    }

    /**
     * Acessa o nome e o sobrenome do contato
     *
     * @return O nome e sobrenome do contato.
     */
    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    /**
     * Retorna a String que representa o cantato.
     * A representação apresentará o nome completo do contato, todos os telefones,
     * e qual deles é utilizado como prioritário e como whatsapp.
     *
     * @return A representação em String de contato.
     */
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append(getNomeCompleto()).append("\n");
        for (int i = 0; i < telefones.length; i++) {
            if (telefones[i].equals("")) continue;
            else if (i == this.prioritario - 1 && i == this.whatsapp - 1) msg.append(telefones[i]).append(" (prioritário)").append(" (zap)\n");
            else if (i == this.prioritario - 1) msg.append(telefones[i]).append(" (prioritário)\n");
            else if (i == this.whatsapp - 1) msg.append(telefones[i]).append(" (zap)\n");
            else msg.append(telefones[i]).append("\n");
        }

        return msg.toString();
    }

    /**
     * Acessa o número prioritário do contato.
     *
     * @return O número prioritário do contato.
     */
    public String getPrioritario() {
        if (this.prioritario == 0) return "Não tem";
        if (this.telefones[this.prioritario - 1].equals("")) return "Não tem";
        return this.telefones[this.prioritario - 1];
    }

    /**
     * Acessa o número utilizado pelo contato para o whatsapp.
     *
     * @return O número utilizado para o whatsapp.
     */
    public String getWhatsapp() {
        if (this.whatsapp == 0) return "Não tem";
        if (this.telefones[this.whatsapp - 1].equals("")) return "Não tem";
        return this.telefones[this.whatsapp - 1];
    }

}
