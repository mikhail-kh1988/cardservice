package ru.cardservice.CardService.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import ru.cardservice.CardService.models.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByCardholder(String cardholder);
    //List<Card> findByAccountNumber(long accountNumber);
    Card findByAccountNumber(long accountNumber);
    long findAccountNumberByCardnumber(long cardnumber);
    Optional<Card> findByCardnumber(long cardNumber);

}
