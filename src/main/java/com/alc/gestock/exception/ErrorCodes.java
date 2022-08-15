package com.alc.gestock.exception;



    public enum ErrorCodes {

        ITEM_NOT_FOUND(1000),
        ITEM_NOT_VALID(1001),
        ITEM_ALREADY_IN_USE(1002),

        CATEGORY_NOT_FOUND(2000),
        CATEGORY_NOT_VALID(2001),
        CATEGORY_ALREADY_IN_USE(2002),

        EMPLOYE_NOT_FOUND(3000),
        EMPLOYE_NOT_VALID(3001),
        EMPLOYE_ALREADY_IN_USE(3002),

        COMMANDE_EMPLOYE_NOT_FOUND(4000),
        COMMANDE_EMPLOYE_NOT_VALID(4001),
        COMMANDE_EMPLOYE_NON_MODIFIABLE(4002),
        COMMANDE_EMPLOYE__ALREADY_IN_USE(4003),


        LIGNE_COMMANDE_EMPLOYE_NOT_FOUND(5000),

        MVT_STK_NOT_FOUND(6000),
        MVT_STK_NOT_VALID(6001),

        UTILISATEUR_NOT_FOUND(7000),
        UTILISATEUR_NOT_VALID(7001),
        UTILISATEUR_CHANGE_PASSWORD_NOT_VALID(7002),

        BAD_CREDENTIALS(8000),

        UPDATE_PHOTO_EXCEPTION(9000),

        UNKNOWN_CONTEXT(11001)
        ;
        private int code;

        ErrorCodes(int code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }


