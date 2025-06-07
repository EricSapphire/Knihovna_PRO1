package knihovna.data;

import java.util.ArrayList;

public class Knihovna {
    //atributy třídy Knihovna
    private ArrayList<Kniha> knihy;
    
    
    //konstruktor třídy Knihovna
    public Knihovna() {
        this.knihy = new ArrayList<>();
        }

    //getter třídy Knihovna

    public ArrayList<Kniha> getKnihy() {
        return knihy;
    }

    //manipulace s knihami v knihovně
    public void pridejKnihu(Kniha kniha) {
        knihy.add(kniha);
    }
    
    public void odeberKnihu(Kniha kniha) {
        knihy.remove(kniha);
    }

    public void upravKnihu(int index, Kniha upravenaKniha) {
        if (index >= 0 && index < knihy.size()) {
            knihy.set(index, upravenaKniha);
        }
    }

    public int pocetKnih() {
        return knihy.size();
    }

    public Kniha get(int row) {
        return knihy.get(row);
    }


}
