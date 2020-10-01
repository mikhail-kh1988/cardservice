package ru.cardservice.CardService.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cardservice.CardService.models.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {
}
