package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CardServiceEndpoint {
    //private static final String NAMESPACE_URI = "http://localhost/cardservice";
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CardRepository cardRepository;

    @Autowired
    public CardServiceEndpoint(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegisterCardRequest")
    @ResponsePayload
    public GetRegisterCardResponse getRegisterCard(@RequestPayload GetRegisterCardRequest registerCard){
        GetRegisterCardResponse response = new GetRegisterCardResponse();
        cardRepository.addCard(registerCard.getCardholder(), registerCard.getAccountNumber());
        response.setCard(cardRepository.findCardByAccount(registerCard.getAccountNumber()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCardInfoRequest")
    @ResponsePayload
    public GetCardInfoResponse getCardInfoRequest(@RequestPayload GetCardInfoRequest request){
        GetCardInfoResponse response = new GetCardInfoResponse();
        //cardRepository.addTemplateCard();
        response.setCard(cardRepository.findCardByAccount(request.getAccountNumber()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRegisterCardRequest")
    @ResponsePayload
    public GetCountRegisterCardResponse getCountRegisterCard(@RequestPayload GetCountRegisterCardRequest request){
        GetCountRegisterCardResponse response = new GetCountRegisterCardResponse();
        response.setCountCard(cardRepository.getCountCard());
        return response;
    }

}
