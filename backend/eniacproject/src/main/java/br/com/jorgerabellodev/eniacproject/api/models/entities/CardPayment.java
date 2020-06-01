package br.com.jorgerabellodev.eniacproject.api.models.entities;

import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;

@Entity
@JsonTypeName("card_payment")
public class CardPayment extends Payment {

    private Integer partsOfPayment;

    public CardPayment() {
    }

    public CardPayment(Long id,
            PaymentStatus paymentStatus,
            Order order,
            Integer partsOfPayment) {
        super(id, paymentStatus, order);
        this.partsOfPayment = partsOfPayment;
    }

    public Integer getPartsOfPayment() {
        return partsOfPayment;
    }

    public void setPartsOfPayment(Integer partsOfPayment) {
        this.partsOfPayment = partsOfPayment;
    }
}
