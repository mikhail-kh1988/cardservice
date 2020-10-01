package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.Card;
import io.spring.guides.gs_producing_web_service.Paysystem;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class CardRepository{
    private static final Map<Long, Card> cards = new HashMap<>();
    public void addCard(String cardholder, long accountNumber){
        Card card = new Card();
        card.setCardholder(cardholder);
        card.setCardnumber(new CardGenerator().getCardNumber());
        card.setCvvCode(new CardGenerator().getCVVcode());
        card.setPaySystem(new CardGenerator().getCardPaySystem());
        card.setActualDate("01 25");

        cards.put(accountNumber, card);
    }

    public Card findCardByAccount(long accountNumber){
        Assert.notNull(accountNumber, "Account number not null.");
        return cards.get(accountNumber);
    }

    public void addTemplateCard(){
        Card card = new Card();
        card.setCardholder("IVANOV I I");
        card.setCardnumber(698754874);
        card.setCvvCode(125);
        card.setActualDate("02 02");
        card.setPaySystem(Paysystem.MIR);
        cards.put(123456L, card);
    }

    public Integer getCountCard(){
        return cards.size();
    }
}
