package ru.cardservice.CardService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String Family;
    private String Name;
    private String FatherName;
    private String Address;
    private long Phone;
    private boolean Status;
    private long Balance;
    private int Card_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long phone) {
        Phone = phone;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public long getBalance() {
        return Balance;
    }

    public void setBalance(long balance) {
        Balance = balance;
    }

    public int getCard_id() {
        return Card_id;
    }

    public void setCard_id(int card_id) {
        Card_id = card_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getID() == account.getID() &&
                getPhone() == account.getPhone() &&
                isStatus() == account.isStatus() &&
                getBalance() == account.getBalance() &&
                getCard_id() == account.getCard_id() &&
                Objects.equals(getFamily(), account.getFamily()) &&
                Objects.equals(getName(), account.getName()) &&
                Objects.equals(getFatherName(), account.getFatherName()) &&
                Objects.equals(getAddress(), account.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getFamily(), getName(), getFatherName(), getAddress(), getPhone(), isStatus(), getBalance(), getCard_id());
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", Family='" + Family + '\'' +
                ", Name='" + Name + '\'' +
                ", FatherName='" + FatherName + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone=" + Phone +
                ", Status=" + Status +
                ", Balance=" + Balance +
                ", Card_id=" + Card_id +
                '}';
    }
}
