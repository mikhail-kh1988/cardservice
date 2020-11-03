package ru.cardservice.CardService.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cardservice.CardService.models.Operations;

public interface OperationRepository extends CrudRepository<Operations, Integer> {
}
