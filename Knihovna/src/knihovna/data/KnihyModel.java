package knihovna.data;

import javax.swing.table.AbstractTableModel;

public class KnihyModel extends AbstractTableModel {
    Knihovna knihy;
    String[] nazvySloupcu = {"Název", "Jméno autora", "Příjmení autora", "Žánr", "Den přidání", "Přečteno"};

    public KnihyModel(Knihovna knihy) {
        this.knihy = knihy;
    }

    @Override
    public int getRowCount() {
        return knihy.pocetKnih();
    }

    public String getColumnName(int col) {
        return nazvySloupcu[col];
    }

    @Override
    public int getColumnCount() {
        return nazvySloupcu.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 5 ? Boolean.class : String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Kniha kniha = knihy.get(row);
        switch (col) {
            case 0 :
                return kniha.getNazevKnihy();
            case 1 :
                return kniha.getJmenoAutora();
            case 2 :
                return kniha.getPrijmeniAutora();
            case 3 :
                return kniha.getZanr();
            case 4 :
                return String.valueOf(kniha.getDenPridani());
            case 5 :
                return kniha.getPrecteno();
            default: return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

//    public void refresh() {
//        fireTableDataChanged();
//    }

    public void setKnihovna(Knihovna knihovna) {
        this.knihy = knihovna;
        fireTableDataChanged();
    }
}
