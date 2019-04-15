import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Gerenciador {

    private Guerreiro raiz = null;
    private ArrayList<String> linhas = new ArrayList<>();

    public Gerenciador(String caso){
        this.carregarTeste(caso);
    }


    public void carregarTeste(String caso) {
        Path path2 = Paths.get("casos/caso"+ caso +".txt");
        try (Scanner sc = new Scanner(Files.newBufferedReader(
                path2, Charset.forName("utf8"))).useDelimiter("[\n]")) {

            while (sc.hasNext()){
                this.linhas.add(sc.nextLine());
            }
            long ti = System.currentTimeMillis();
            this.runTeste();
            long tf = System.currentTimeMillis();
            System.out.println("Tempo do teste: " + 1.0 * ((tf - ti) / 1000) + " segundos");
            System.out.println("Gerando digraph");
            this.diGraph(caso);

        } catch (IOException e) {
            System.err.println("Erro de arquivos!");
        } catch (NumberFormatException number){
            System.err.println("Erro de formatação de números!");
        }
    }

    public int maiorGeracao = 0;
    public ArrayList<Guerreiro> pertencentes;

    public void runTeste(){
        String primeiraLinha = this.linhas.remove(0);
        int terrasIniciais = Integer.parseInt(primeiraLinha);

        this.pertencentes = new ArrayList<>();

        for(String linha: this.linhas){
            String[] linhaSeparada = linha.split("\\s+");

            if(this.raiz == null) {
                this.raiz = new Guerreiro(null,linhaSeparada[0],terrasIniciais);
            }

            Guerreiro pai = raiz.procurarFilhoPorNome(linhaSeparada[0]);
            int terras = Integer.parseInt(linhaSeparada[2]);
            Guerreiro filho = pai.procurarSomenteFIlhoDestePorNome(linhaSeparada[1]);
            if(filho == null){
                filho = new Guerreiro(pai,linhaSeparada[1],terras);
                if(filho.getGeracao() > maiorGeracao){
                    this.maiorGeracao = filho.getGeracao();
                    pertencentes = new ArrayList<>();
                }
                if(filho.getGeracao() == maiorGeracao){
                    pertencentes.add(filho);
                }

            }
        }

        raiz.dividirTerrasEntreFilhosRecursivamente();
        System.out.println("Maior geracao: " + maiorGeracao);
        System.out.println("Numero de guerreiros nela " + pertencentes.size());
        if(pertencentes.size() == 0) return;
        Guerreiro maior = pertencentes.get(0);
        for(Guerreiro g: pertencentes){
            if(g.getTerras() > maior.getTerras()){
                maior = g;
            }
        }
        System.out.println("Maior guerreiro: " + maior.getNome() + ", com " + maior.getTerras() + " terras");

    }

    public void diGraph(String caso){
        Graph graph = new Graph();
        for(String linha: this.linhas){
            if(!linha.matches("(\\w+(\\s)\\w+(\\s)\\w+)")){
                continue;
            }
            String[] linhaSeparada = linha.split("\\s+");
            graph.createConnection(linhaSeparada[0],linhaSeparada[1w]);
        }
        graph.geraArquivo(caso);

    }
}
