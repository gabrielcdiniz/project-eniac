package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.models.entities.ExchangePayment;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    public void fillExchangePayment(ExchangePayment exchangePayment, Date orderDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        exchangePayment.setDueDate(calendar.getTime());
    }
}
