package atm.entites;

import java.util.UUID;

public class Card {
        private final String cardId;
        private int pin;

        public String getCardId() {
            return cardId;
        }

        public Card(Bank bank,int pin) {
            String bankId=bank.getBankId();
            this.cardId = UUID.randomUUID() + bankId.substring(bankId.length() - 4);
            this.pin = pin;
        }

        public int getPin() {
            return pin;
        }

        public  void setPin(int pin) {
            this.pin = pin;
        }

    }
