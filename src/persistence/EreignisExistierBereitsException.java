package src.persistence;

import src.valueObjects.Artikel;

public class EreignisExistierBereitsException extends Throwable {


    public EreignisExistierBereitsException(Artikel artikel, int menge) {
        super("Artikel " + artikel + " mit Menge " + menge +  " existiert bereits im Warenkorb.");

    }
}
