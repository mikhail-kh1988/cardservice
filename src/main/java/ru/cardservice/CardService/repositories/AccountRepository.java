package ru.cardservice.CardService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cardservice.CardService.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    //Account findById(Integer id);
    //int findBalanceById(int id);
    //String findFamilyById(int id);
    //String findFather_nameById(int id);
    //String findNameById(int id);
}
