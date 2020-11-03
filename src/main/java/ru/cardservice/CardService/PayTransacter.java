package ru.cardservice.CardService;

import io.spring.guides.gs_producing_web_service.Bill;
import io.spring.guides.gs_producing_web_service.GetPayRequest;
import io.spring.guides.gs_producing_web_service.GetPayRequest;
import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cardservice.CardService.models.Account;
import ru.cardservice.CardService.models.Card;
import ru.cardservice.CardService.models.Operations;
import ru.cardservice.CardService.repositories.AccountRepository;
import ru.cardservice.CardService.repositories.CardRepository;
import ru.cardservice.CardService.repositories.OperationRepository;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Optional;

class PayTransacter {

    private AccountRepository   accountRepository;
    private CardRepository      cardRepository;
    private OperationRepository operationRepository;
    private Bill bill;

    @Autowired
    public PayTransacter(OperationRepository operationRep, CardRepository cardRepo, AccountRepository accountRepo){
        accountRepository   = accountRepo;
        cardRepository      = cardRepo;
        operationRepository = operationRep;
    }

    public int payOperation(GetPayRequest pay){
        Operations operation    = new Operations();
        Account sender = getAccountByCardNumber(pay.getCardholdernumber());
        Account recipient = getAccountByCardNumber(pay.getRecipientcardnumber());
        if (requestMaybeSetTransaction(sender.getID(), pay.getPaySum())){
            this.accountRepository.save(payOperationForSender(sender, pay.getPaySum()));
            //TODO Insert information into log file or other source information.
            //save information in log
            this.accountRepository.save(payOperationForRecipient(recipient, pay.getPaySum()));
            operation.setCard_recipient(pay.getRecipientcardnumber());
            operation.setCard_sender(pay.getCardholdernumber());
            operation.setDate_operation(new DateType());
            operation.setSum(pay.getPaySum());
            operation.setID(this.operationRepository.count()+1);
            bill = new Bill();
            bill.setDateOperation(null);
            String tempFio;
            bill.setCardholderFio(sender.getFamily()+" "+sender.getName()+" "+sender.getFatherName());
            bill.setCurrentAccountBalance((int) sender.getBalance());
            bill.setSumOperation(pay.getPaySum());
            return 1;
        }else
            return 2;
    }

    public Bill getBillOperation(){
        return bill;
    }

    private boolean requestMaybeSetTransaction(int AccountIdSender, long sum){
        Optional<Account> getAccount = this.accountRepository.findById(AccountIdSender);
        Account account = getAccount.get();
        if (account.getBalance() > sum)
            return true;
        return false;
    }

    private Account payOperationForRecipient(Account recipient, long sum){
        long currentRecipientBalance = recipient.getBalance();
        recipient.setBalance(currentRecipientBalance + sum);
        return recipient;
    }

    private Account payOperationForSender(Account sender, long sum){
        long currentSenderBalance = sender.getBalance();
        sender.setBalance(currentSenderBalance - sum);
        return sender;
    }

    private Account getAccountByCardNumber(long cardNumber){
        long number = this.cardRepository.findAccountNumberByCardnumber(cardNumber);
        Optional<Account> accountOptional = this.accountRepository.findById((int)number);
        return accountOptional.get();
    }


}
