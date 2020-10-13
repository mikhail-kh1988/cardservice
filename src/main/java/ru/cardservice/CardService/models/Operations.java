package ru.cardservice.CardService.models;

import org.hibernate.type.DateType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private long card_recipient;
    private long card_sender;
    private long sum;
    private DateType date_operation;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCard_recipient() {
        return card_recipient;
    }

    public void setCard_recipient(long card_recipient) {
        this.card_recipient = card_recipient;
    }

    public long getCard_sender() {
        return card_sender;
    }

    public void setCard_sender(long card_sender) {
        this.card_sender = card_sender;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public DateType getDate_operation() {
        return date_operation;
    }

    public void setDate_operation(DateType date_operation) {
        this.date_operation = date_operation;
    }
}
