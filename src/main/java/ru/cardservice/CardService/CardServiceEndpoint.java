package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.cardservice.CardService.models.Account;
import ru.cardservice.CardService.models.Card;
import ru.cardservice.CardService.repositories.AccountRepository;
import ru.cardservice.CardService.repositories.OperationRepository;

import java.util.List;

@Endpoint
public class CardServiceEndpoint {
    //private static final String NAMESPACE_URI = "http://localhost/cardservice";
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ru.cardservice.CardService.repositories.CardRepository cardRepository;
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @Autowired
    public CardServiceEndpoint(ru.cardservice.CardService.repositories.CardRepository cardRepository,
                               AccountRepository accountRepository,
                               OperationRepository operationRepository){
        this.cardRepository         = cardRepository;
        this.accountRepository      = accountRepository;
        this.operationRepository    = operationRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRegisterCardRequest")
    @ResponsePayload
    public GetRegisterCardResponse getRegisterCard(@RequestPayload GetRegisterCardRequest registerCard){
        GetRegisterCardResponse response = new GetRegisterCardResponse();
        Card card = new Card();
        card.setCardholder(registerCard.getCardholder());
        card.setAccountNumber(registerCard.getAccountNumber());
        card.setPaySystem(CardGenerator.getCardPaySystem());
        card.setActualDate("01 22");
        card.setCardnumber(CardGenerator.getCardNumber());
        card.setCvvCode(CardGenerator.getCVVcode());
        card.setStatus(true);
        cardRepository.save(card);
        //cardRepository.addCard(registerCard.getCardholder(), registerCard.getAccountNumber());
        //response.setCard((io.spring.guides.gs_producing_web_service.Card) cardRepository.findById(1));
        //response.setCard(cardRepository.findCardByAccount(registerCard.getAccountNumber()));
        cardRepository.getOne(card.getID());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCardInfoRequest")
    @ResponsePayload
    public GetCardInfoResponse getCardInfoRequest(@RequestPayload GetCardInfoRequest request){
        GetCardInfoResponse response = new GetCardInfoResponse();
        //cardRepository.addTemplateCard();
        //response.setCard(cardRepository.findCardByAccount(request.getAccountNumber()));
        //List<Card> temp = cardRepository.findByAccountNumber(request.getAccountNumber());
        /*io.spring.guides.gs_producing_web_service.Card tmpCard = new io.spring.guides.gs_producing_web_service.Card();
        if (temp.size() > 0){
            tmpCard.setCardholder(temp.get(0).getCardholder());
            tmpCard.setPaySystem(temp.get(0).getPaySystem());
            tmpCard.setActualDate(temp.get(0).getActualDate());
            tmpCard.setCvvCode(temp.get(0).getCvvCode());
            tmpCard.setCardnumber(temp.get(0).getCardnumber());
            response.setCard(tmpCard);
            return response;
        }else {
            return response;
        }
        */
        Card temp = cardRepository.findByAccountNumber(request.getAccountNumber());
        io.spring.guides.gs_producing_web_service.Card card = new io.spring.guides.gs_producing_web_service.Card();
        card.setCardholder(temp.getCardholder());
        card.setPaySystem(temp.getPaySystem());
        card.setActualDate(temp.getActualDate());
        card.setCvvCode(temp.getCvvCode());
        card.setCardnumber(temp.getCardnumber());
        response.setCard(card);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRegisterCardRequest")
    @ResponsePayload
    public GetCountRegisterCardResponse getCountRegisterCard(@RequestPayload GetCountRegisterCardRequest request){
        GetCountRegisterCardResponse response = new GetCountRegisterCardResponse();
        //response.setCountCard(cardRepository.getCountCard());
        response.setCountCard((int)cardRepository.count());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPayRequest")
    @ResponsePayload
    public GetPayResponse responseSuccessPay(@RequestPayload GetPayRequest requestPay){
        GetPayResponse response = new GetPayResponse();
        //ResponseErrorPay responseErrorPay = new ResponseErrorPay();
        PayTransacter transacter = new PayTransacter(operationRepository, cardRepository, accountRepository);
        transacter.payOperation(requestPay);
        response.setBill(transacter.getBillOperation());
        return response;
    }


}
