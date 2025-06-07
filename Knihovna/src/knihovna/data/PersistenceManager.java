package knihovna.data;

/**
 * Rozhraní pro persistenci dat
 */

public interface PersistenceManager {
    /**
     * Uloží všechny položky v Knihovně do souboru
     * @param knihovna
     * @throws PersistenceException
     */
    void ulozitVse(Knihovna knihovna) throws PersistenceException;

    /**
     * Načte všechny položky ze souboru do Knihovny
     * @return
     * @throws PersistenceException
     */
    Knihovna nacistVse() throws PersistenceException;
}
