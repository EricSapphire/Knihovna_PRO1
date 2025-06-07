package knihovna.gui;

import knihovna.data.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KnihovnaFrame extends JFrame implements ActionListener {
    private Knihovna knihovna = new Knihovna();
    KnihyModel knihyModel = new KnihyModel(knihovna);
    private JTable jtVypisKnih = new JTable(knihyModel);
    private PersistenceManager persistenceManager = new CsvPersistenceManager("data.csv");
    TableRowSorter<KnihyModel> sorter = new TableRowSorter<>(knihyModel);

    //Konstruktor GUI
    public KnihovnaFrame() {
        super("Knihovna");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGui();
        jtVypisKnih.setRowSorter(sorter);

    }

    private void initGui() {
        createToolbar();
        add(new JScrollPane(jtVypisKnih), BorderLayout.CENTER);

        pack();
    }

    //Vytvoření toolbaru s tlačítky
    private void createToolbar() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        add(tb, BorderLayout.NORTH);

        JButton btPridej = new JButton("Nová kniha");
        JButton btEdituj = new JButton("Edituj knihu");
        JButton btSmaz = new JButton("Smaž knihu");

        JButton btNahrej = new JButton("Nahrát soubor");
        JButton btUloz = new JButton("Uložit do souboru");

        JTextField tfHledat = new JTextField(20);
        JLabel lblHledat = new JLabel("Hledej:");

        btPridej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KnihaDialog dlg = new KnihaDialog(KnihovnaFrame.this);
                Kniha k =  dlg.vytvorNovouKnihu();
                if (k != null) {
                   knihovna.pridejKnihu(k);
                   knihyModel.fireTableDataChanged();
                }
            }
        });

        btEdituj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vybranaKniha = jtVypisKnih.getSelectedRow();
                Kniha puvodniKniha = knihovna.getKnihy().get(vybranaKniha);
                KnihaDialog dialog = new KnihaDialog(null);
                Kniha upravenaKniha = dialog.upravKnihu(puvodniKniha);

                if (upravenaKniha != null) {
                    knihovna.upravKnihu(vybranaKniha, upravenaKniha);
                    knihyModel.fireTableRowsUpdated(vybranaKniha, vybranaKniha);
                }
            }
        });

        btSmaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vybranaKniha = jtVypisKnih.getSelectedRow();

                int potvrzeni = JOptionPane.showConfirmDialog(null, "Opravdu chceš tuto knihu smazat z knihovny?", "Opravdu chceš smazat knihu?", JOptionPane.YES_NO_OPTION);

                if(potvrzeni == JOptionPane.YES_OPTION) {
                    knihovna.odeberKnihu(knihovna.getKnihy().get(vybranaKniha));
                    knihyModel.fireTableDataChanged();
                }

            }
        });

        btUloz.addActionListener((e -> ulozit()));
        btNahrej.addActionListener((e -> nacist()));

        tfHledat.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Filter(tfHledat, sorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Filter(tfHledat, sorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Filter(tfHledat, sorter);
            }
        });


        tb.add(btPridej);
        tb.add(btEdituj);
        tb.add(btSmaz);
        tb.addSeparator();
        tb.add(btUloz);
        tb.add(btNahrej);
        tb.addSeparator();
        tb.add(lblHledat);
        tb.add(tfHledat);

    }

    private void nacist() {
        try {
            knihovna = persistenceManager.nacistVse();
            knihyModel.setKnihovna(knihovna);
            JOptionPane.showMessageDialog(this, "Data byla načtena", "Načtení", JOptionPane.INFORMATION_MESSAGE);
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(KnihovnaFrame.this, e.getMessage(), "Chyba!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ulozit() {
        try {
            persistenceManager.ulozitVse(knihovna);
            JOptionPane.showMessageDialog(this, "Data byla uložena", "Uložení", JOptionPane.INFORMATION_MESSAGE);
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(KnihovnaFrame.this, e.getMessage(), "Chyba!", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void Filter(JTextField filterText, TableRowSorter<KnihyModel> sorter) {
        String hledanyText = filterText.getText().trim().toLowerCase();

        if (hledanyText.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        sorter.setRowFilter(new RowFilter<KnihyModel, Integer>() {
            @Override
            public boolean include(Entry<? extends KnihyModel, ? extends Integer> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    Object hodnota = entry.getValue(i);
                    if (hodnota != null && hodnota.toString().toLowerCase().contains(hledanyText)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }



}
