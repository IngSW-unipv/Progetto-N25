package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.model.transaction.TransactionType;
import it.unipv.ingsw.lasout.model.transaction.exception.UnrecognizedTypeException;

public enum CashbookType {
    DEFAULT(0),
    GENERIC(1);

    private final int code;

    CashbookType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * dato uno specifico codice int si imposta al valore corretto della enum
     * */
    public static CashbookType fromCode(int code) {
        for (CashbookType type : CashbookType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new RuntimeException("Cashbook type not recognized: " + code);
    }
}
