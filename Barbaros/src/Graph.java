import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Graph {
    private ArrayList<String> lista;
    private String inicio,fim;

    /**
     * Método Construtor da Classe
     */
    public Graph(){
        inicio = "digraph G{";
        fim = "}";
        lista = new ArrayList<>();
        lista.add(inicio);
    }

    /**
     * Método que cria um final temporário
     * para poder ser retornado a lista completa de conexões
     * sem erros de código
     * @return ArrayList com o necessário para fazer o GVEDIT ler a árvore
     */
    public ArrayList<String> getDiGraph(){
        ArrayList<String> temp = this.lista;
        temp.add(fim);
        return temp;
    }

    /**
     * Método que gera uma conexão e à adiciona à lista de conexoes da classe
     * @param start primeiro item da conexão
     * @param end segundo item da conexão
     */
    public void createConnection(String start, String end){
        Connection c = new Connection(start,end);
        this.lista.add(c.getConnection());
    }

    /**
     * Método que gera o arquivo ".gv" para poder ser gerado
     * e visualizado pelo "gvedit", programa do Graphvizz, que edita o código das
     * árvores
     * @param caso
     */
    public void geraArquivo(String caso){
        File pastaItens = new File("Graph");
        if(!pastaItens.exists())pastaItens.mkdir();
        Path path1 = Paths.get("Graph/Caso" + caso + ".gv");
        try (PrintWriter arquivo = new PrintWriter(Files.newBufferedWriter(path1, Charset.forName("utf8")))) {
            for(String s: getDiGraph()){
                arquivo.println(s);
            }
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo!");
        }
    }
}
