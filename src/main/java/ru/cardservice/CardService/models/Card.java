package ru.cardservice.CardService.models;

import io.spring.guides.gs_producing_web_service.Paysystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String cardholder;
    private long cardnumber;
    private int cvvCode;
    private String actualDate;
    private Paysystem paySystem;
    private long accountNumber;

    public Card() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardholder() {
        return this.cardholder;
    }

    public void setCardholder(String value) {
        this.cardholder = value;
    }

    public long getCardnumber() {
        return this.cardnumber;
    }

    public void setCardnumber(long value) {
        this.cardnumber = value;
    }

    public int getCvvCode() {
        return this.cvvCode;
    }

    public void setCvvCode(int value) {
        this.cvvCode = value;
    }

    public String getActualDate() {
        return this.actualDate;
    }

    public void setActualDate(String value) {
        this.actualDate = value;
    }

    public Paysystem getPaySystem() {
        return this.paySystem;
    }

    public void setPaySystem(Paysystem value) {
        this.paySystem = value;
    }
}
