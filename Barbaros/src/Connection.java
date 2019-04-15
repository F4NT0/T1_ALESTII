public class Connection {

    private String start;
    private String end;

    public Connection(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getConnection(){
        return start + "->" + end + ";";
    }
}
