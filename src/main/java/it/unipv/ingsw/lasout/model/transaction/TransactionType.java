package it.unipv.ingsw.lasout.model.transaction;

import it.unipv.ingsw.lasout.model.transaction.exception.UnrecognizedTypeException;

public enum TransactionType {
    AUTOMATIC(0),
    MANUAL(1);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * dato uno specifico codice int si imposta al valore corretto della enum
     * */
    public static TransactionType fromCode(int code) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new UnrecognizedTypeException("Transaction type not recognized: " + code);
    }
}