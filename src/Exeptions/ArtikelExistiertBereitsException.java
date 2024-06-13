package src.Exeptions;

public class ArtikelExistiertBereitsException extends Throwable {

    public ArtikelExistiertBereitsException(int artikelNummer, String bezeichnung) {
        super("Artikel mit bezeichnung" + bezeichnung + " ArtikelNummer" + artikelNummer + "esistiert bereits!");
    }

}
