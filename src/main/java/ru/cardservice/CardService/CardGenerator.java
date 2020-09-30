package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.Paysystem;

import java.util.Random;

class CardGenerator {

    public static long getCardNumber(){
        return new Random(99999999).nextLong();
    }

    public static int getCVVcode(){
        return new Random(999).nextInt();
    }

    public static Paysystem getCardPaySystem(){
        switch (new Random(4).nextInt()){
            case 1: return Paysystem.MASTERCARD;
            case 2: return Paysystem.UNIONPAY;
            case 3: return Paysystem.MIR;
            case 4: return Paysystem.VISA;
            default:
                return Paysystem.MIR;
        }
    }

}
