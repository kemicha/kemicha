package src.persistence;

public class WarenkorbExistierBereitsException extends Throwable {


    public WarenkorbExistierBereitsException(int artikel, String menge) {
        super("Artikel " + artikel + " mit Menge " + menge + " existiert bereits im Warenkorb.");

    }
}
