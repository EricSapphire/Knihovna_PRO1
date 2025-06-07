package knihovna.gui;

import knihovna.data.Kniha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class KnihaDialog extends JDialog {
    private JTextField tfNazev = new JTextField(25);
    private JTextField tfJmeno = new JTextField(15);
    private JTextField tfPrijmeni = new JTextField(15);
    private JTextField tfZanr = new JTextField(10);
    private JTextField tfDatum = new JTextField(10);
    private JCheckBox cbPrecteno = new JCheckBox();
    private JButton btOK;
    private JButton btCancel;

    private boolean ok = false;


    public KnihaDialog(Frame owner) {
        super(owner, "Přidání knihy", true);

        initGui();
    }

    private void initGui() {

        JPanel pnlCentral = new JPanel(new GridLayout(3,2
        ));
        JLabel lblDatum = new JLabel("Datum přidání");
        lblDatum.setLabelFor(tfDatum);
        lblDatum.setDisplayedMnemonic('D');
        pnlCentral.add(lblDatum);
        pnlCentral.add(tfDatum);

        JLabel lblNazev = new JLabel("Název");
        lblNazev.setLabelFor(tfNazev);
        lblNazev.setDisplayedMnemonic('N');
        pnlCentral.add(lblNazev);
        pnlCentral.add(tfNazev);

        JLabel lblJmeno = new JLabel("Jméno autora");
        lblJmeno.setLabelFor(tfJmeno);
        lblJmeno.setDisplayedMnemonic('J');
        pnlCentral.add(lblJmeno);
        pnlCentral.add(tfJmeno);

        JLabel lblPrijmeni = new JLabel("Prijmeni autora");
        lblPrijmeni.setLabelFor(tfPrijmeni);
        lblPrijmeni.setDisplayedMnemonic('P');
        pnlCentral.add(lblPrijmeni);
        pnlCentral.add(tfPrijmeni);

        JLabel lblZanr = new JLabel("Žánr");
        lblZanr.setLabelFor(tfZanr);
        lblZanr.setDisplayedMnemonic('Z');
        pnlCentral.add(lblZanr);
        pnlCentral.add(tfZanr);

        JLabel lblPrecteno = new JLabel("Přečteno");
        lblPrecteno.setLabelFor(cbPrecteno);
        lblPrecteno.setDisplayedMnemonic('R');
        pnlCentral.add(lblPrecteno);
        pnlCentral.add(cbPrecteno);

        add(pnlCentral, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btOK = new JButton("Potvrdit");
        btCancel = new JButton("Zrušit");

        pnlButtons.add(btOK);
        pnlButtons.add(btCancel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = e.getSource() == btOK;
                setVisible(false);
            }
        };

        btOK.addActionListener(listener);
        btCancel.addActionListener(listener);

        getRootPane().setDefaultButton(btOK);

        add(pnlButtons, BorderLayout.SOUTH);

        pack();
    }

    public Kniha vytvorNovouKnihu() {
        tfNazev.setText("");
        tfJmeno.setText("");
        tfPrijmeni.setText("");
        tfZanr.setText("");
        tfDatum.setText(LocalDate.now().toString());
        cbPrecteno.setSelected(false);
        setVisible(true);
        if (ok) {
            Kniha k = new Kniha(tfNazev.getText(), tfJmeno.getText(), tfPrijmeni.getText(), tfZanr.getText(), tfDatum.getText(), cbPrecteno.isSelected());

            return k;
        } else {
            return null;
        }
    }

    public Kniha upravKnihu(Kniha k) {
        tfNazev.setText(k.getNazevKnihy());
        tfJmeno.setText(k.getJmenoAutora());
        tfPrijmeni.setText(k.getPrijmeniAutora());
        tfZanr.setText(k.getZanr());
        tfDatum.setText(k.getDenPridani());
        cbPrecteno.isSelected();
        setVisible(true);
        if (ok) {
            Kniha kNew = new Kniha(tfNazev.getText(), tfJmeno.getText(), tfPrijmeni.getText(), tfZanr.getText(), tfDatum.getText(), cbPrecteno.isSelected());
            return kNew;
        } else {
            return null;
        }
    }
}
