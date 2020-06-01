package br.com.jorgerabellodev.eniacproject.api.models.entities;

import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Date;
import javax.persistence.Entity;

@Entity
@JsonTypeName("exchange_payment")
public class ExchangePayment extends Payment {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public ExchangePayment() {
    }

    public ExchangePayment(
            Long id,
            PaymentStatus paymentStatus,
            Order order,
            Date dueDate,
            Date paymentDate) {
        super(id, paymentStatus, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
