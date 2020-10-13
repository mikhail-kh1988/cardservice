package ru.cardservice.CardService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cardservice.CardService.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
}
