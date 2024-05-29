package src.persistence;

import src.valueObjects.Artikel;

public class WarenkorbExistierBereitsException extends Throwable {


    public WarenkorbExistierBereitsException(Artikel artikel, int menge) {
        super("Artikel " + artikel + " mit Menge " + menge + " existiert bereits im Warenkorb.");

    }
}
