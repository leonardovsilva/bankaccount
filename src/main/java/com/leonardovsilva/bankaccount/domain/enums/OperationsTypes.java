package com.leonardovsilva.bankaccount.domain.enums;

public enum OperationsTypes {
    PAY_IN_CASH(1), PURCHASE_IN_INSTALLMENTS(2), WITHDRAW(3), PAYMENT(4);

    private final int value;

    OperationsTypes(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean Compare(int i){return value == i;}

    public static OperationsTypes GetValue(int value)
    {
        OperationsTypes[] As = OperationsTypes.values();
        for(int i = 0; i < As.length; i++)
        {
            if(As[i].Compare(value))
                return As[i];
        }
        return null;
    }
}
