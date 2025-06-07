package knihovna.data;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvPersistenceManager implements PersistenceManager {
    private String fileName;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public CsvPersistenceManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ulozitVse(Knihovna knihovna) throws PersistenceException {
        try(PrintWriter out = new PrintWriter(new FileOutputStream(fileName))) {
            for(Kniha k : knihovna.getKnihy()) {
                out.printf("%s;%s;%s;%s;%s;%b\n", k.getNazevKnihy(), k.getJmenoAutora(), k.getPrijmeniAutora(), k.getZanr(), k.getDenPridani(), k.getPrecteno());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba! Soubor " + fileName + " nenalezen!", e);
        }
    }

    @Override
    public Knihovna nacistVse() throws PersistenceException {
        Knihovna knihovna = new Knihovna();

        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            String radek;

            while((radek = bfr.readLine()) != null) {
                String[] pole = radek.split(";");
                boolean precteno = Boolean.parseBoolean(pole[5]);
                knihovna.pridejKnihu(new Kniha(pole[0], pole[1], pole[2], pole[3], pole[4], precteno));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Soubor se nepodařilo otevřít", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba při čtení souboru", e);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new PersistenceException("Chybný formát data", e);
        }

        return knihovna;
    }
}
