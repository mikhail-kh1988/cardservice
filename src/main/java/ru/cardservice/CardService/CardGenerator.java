package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.Paysystem;

import java.util.Random;

class CardGenerator {

    private static Random random = new Random();

    public static long getCardNumber(){
        return (long)random.nextInt(999999999);
    }

    public static int getCVVcode(){
        int temp = random.nextInt(999);
        if (temp <= 99)
            temp = random.nextInt(999);
        return temp;
    }

    public static Paysystem getCardPaySystem(){
        switch (random.nextInt(4)){
            case 1: return Paysystem.MASTERCARD;
            case 2: return Paysystem.UNIONPAY;
            case 3: return Paysystem.MIR;
            case 4: return Paysystem.VISA;
            default:
                return Paysystem.MIR;
        }
    }

}
