package models;

public class BillingCard {
    public String cardNumber;
    public String month;
    public String year;
    public String cardHolderName;

    public BillingCard(String cardNumber, String month, String year, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CharSequence getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

}
