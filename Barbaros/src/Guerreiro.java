import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guerreiro {
    private Guerreiro pai;
    private String nome;
    private int terras;
    private List<Guerreiro> filhos;
    private int geracao = 1;

    public Guerreiro(Guerreiro pai, String nome, int terras){
        this.pai = pai;
        this.nome = nome;
        this.terras = terras;
        this.filhos = new ArrayList<>();
        if(this.pai != null){
            this.geracao = pai.getGeracao() + 1;
            this.pai.pushFilhos(this);
        }
    }

    public Guerreiro getPai() {
        return pai;
    }

    public void setPai(Guerreiro pai) {
        this.pai = pai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTerras() {
        return terras;
    }

    public void setTerras(int terras) {
        this.terras = terras;
    }

    public void aumentarTerras(int terrasNovas){
        this.terras += terrasNovas;
    }

    public List<Guerreiro> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<Guerreiro> filhos) {
        this.filhos = filhos;
    }

    public void pushFilhos(Guerreiro... filhos){
        this.filhos.addAll(Arrays.asList(filhos));
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public void dividirTerrasEntreFilhosRecursivamente(){
        if(filhos.size() == 0) return;
        int terrasPorFilho = (int) this.terras / this.filhos.size();
        for(Guerreiro filho: this.filhos){
            filho.aumentarTerras(terrasPorFilho);
            filho.dividirTerrasEntreFilhosRecursivamente();
        }
    }

    public Guerreiro procurarSomenteFIlhoDestePorNome(String nome){
        for(Guerreiro filho: this.filhos){
            if(filho.getNome().equals(nome)){
                return filho;
            }
        }
        return null;
    }

    public Guerreiro procurarFilhoPorNome(String nome){
        if(this.getNome().equals(nome)){
            return this;
        }
        for(Guerreiro filho: this.filhos){
            Guerreiro aux = filho.procurarFilhoPorNome(nome);
            if(aux != null){
                return aux;
            }
        }
        return null;
    }

}
