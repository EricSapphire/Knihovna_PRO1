package knihovna.data;

public class Kniha {
    //atributy třídy Kniha
    private String nazevKnihy;
    private String jmenoAutora;
    private String prijmeniAutora;
    private String zanr;
    private String denPridani;
    private boolean precteno;
    
    //konstruktor třídy Kniha
    public Kniha(String nazevKnihy, String jmenoAutora, String prijmeniAutora, String zanr, String denPridani, boolean precteno) {
        this.nazevKnihy = nazevKnihy;
        this.jmenoAutora = jmenoAutora;
        this.prijmeniAutora = prijmeniAutora;
        this.zanr = zanr;
        this.denPridani = denPridani;
        this.precteno = precteno;
        }
    
    //settery třídy Kniha
    public void setNazevKnihy(String nazevKnihy) {
        this.nazevKnihy=nazevKnihy;
    }    
    
    public void setJmenoAutora(String jmenoAutora) {
        this.jmenoAutora = jmenoAutora;
    }
    
    public void setPrijmeniAutora(String prijmeniAutora) {
        this.prijmeniAutora = prijmeniAutora;
    }
    
    public void setZanr(String zanr) {
        this.zanr = zanr;
    }
    
    public void setDenPridani(String denPridani) {
        this.denPridani = denPridani;
    }
    
    public void setPrecteno(boolean precteno) {
        this.precteno = precteno;
    }
    
    //gettery třídy Kniha
    public String getNazevKnihy() {

        return nazevKnihy;
    }    
    
    public String getJmenoAutora() {
        return jmenoAutora;
    }
    
    public String getPrijmeniAutora() {
        return prijmeniAutora;
    }
    
    public String getZanr() {
        return zanr;
    }
    
    public String getDenPridani() {
        return denPridani;
    }
    
    public boolean getPrecteno() {
        return precteno;
    }

}
