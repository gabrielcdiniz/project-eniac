package br.com.jorgerabellodev.eniacproject.api.services;

import static java.util.Arrays.asList;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Address;
import br.com.jorgerabellodev.eniacproject.api.models.entities.CardPayment;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.models.entities.City;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.models.entities.ExchangePayment;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Order;
import br.com.jorgerabellodev.eniacproject.api.models.entities.OrderItem;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Payment;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Product;
import br.com.jorgerabellodev.eniacproject.api.models.entities.State;
import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.CustomerType;
import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.PaymentStatus;
import br.com.jorgerabellodev.eniacproject.api.repositories.AddressRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.CategoryRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.CityRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.CustomerRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.OrderItemRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.OrderRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.PaymentRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.ProductRepository;
import br.com.jorgerabellodev.eniacproject.api.repositories.StateRepository;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DBService(CategoryRepository categoryRepository,
            ProductRepository productRepository,
            StateRepository stateRepository,
            CityRepository cityRepository,
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void instantiateDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", new BigDecimal("2000.00"));
        Product p2 = new Product(null, "Impressora", new BigDecimal("800.00"));
        Product p3 = new Product(null, "Mouse", new BigDecimal("80.00"));
        Product p4 = new Product(null, "Mesa de escritório", new BigDecimal("300.00"));
        Product p5 = new Product(null, "Toalha", new BigDecimal("50.00"));
        Product p6 = new Product(null, "Colcha", new BigDecimal("200.00"));
        Product p7 = new Product(null, "TV true color", new BigDecimal("1200.00"));
        Product p8 = new Product(null, "Roçadeira", new BigDecimal("800.00"));
        Product p9 = new Product(null, "Abajour", new BigDecimal("100.00"));
        Product p10 = new Product(null, "PENDING", new BigDecimal("180.00"));
        Product p11 = new Product(null, "Shampoo", new BigDecimal("90.00"));

        Product p12 = new Product(null, "Product 12", new BigDecimal("10.00"));
        Product p13 = new Product(null, "Product 13", new BigDecimal("10.00"));
        Product p14 = new Product(null, "Product 14", new BigDecimal("10.00"));
        Product p15 = new Product(null, "Product 15", new BigDecimal("10.00"));
        Product p16 = new Product(null, "Product 16", new BigDecimal("10.00"));
        Product p17 = new Product(null, "Product 17", new BigDecimal("10.00"));
        Product p18 = new Product(null, "Product 18", new BigDecimal("10.00"));
        Product p19 = new Product(null, "Product 19", new BigDecimal("10.00"));
        Product p20 = new Product(null, "Product 20", new BigDecimal("10.00"));
        Product p21 = new Product(null, "Product 21", new BigDecimal("10.00"));
        Product p22 = new Product(null, "Product 22", new BigDecimal("10.00"));
        Product p23 = new Product(null, "Product 23", new BigDecimal("10.00"));
        Product p24 = new Product(null, "Product 24", new BigDecimal("10.00"));
        Product p25 = new Product(null, "Product 25", new BigDecimal("10.00"));
        Product p26 = new Product(null, "Product 26", new BigDecimal("10.00"));
        Product p27 = new Product(null, "Product 27", new BigDecimal("10.00"));
        Product p28 = new Product(null, "Product 28", new BigDecimal("10.00"));
        Product p29 = new Product(null, "Product 29", new BigDecimal("10.00"));
        Product p30 = new Product(null, "Product 30", new BigDecimal("10.00"));
        Product p31 = new Product(null, "Product 31", new BigDecimal("10.00"));
        Product p32 = new Product(null, "Product 32", new BigDecimal("10.00"));
        Product p33 = new Product(null, "Product 33", new BigDecimal("10.00"));
        Product p34 = new Product(null, "Product 34", new BigDecimal("10.00"));
        Product p35 = new Product(null, "Product 35", new BigDecimal("10.00"));
        Product p36 = new Product(null, "Product 36", new BigDecimal("10.00"));
        Product p37 = new Product(null, "Product 37", new BigDecimal("10.00"));
        Product p38 = new Product(null, "Product 38", new BigDecimal("10.00"));
        Product p39 = new Product(null, "Product 39", new BigDecimal("10.00"));
        Product p40 = new Product(null, "Product 40", new BigDecimal("10.00"));
        Product p41 = new Product(null, "Product 41", new BigDecimal("10.00"));
        Product p42 = new Product(null, "Product 42", new BigDecimal("10.00"));
        Product p43 = new Product(null, "Product 43", new BigDecimal("10.00"));
        Product p44 = new Product(null, "Product 44", new BigDecimal("10.00"));
        Product p45 = new Product(null, "Product 45", new BigDecimal("10.00"));
        Product p46 = new Product(null, "Product 46", new BigDecimal("10.00"));
        Product p47 = new Product(null, "Product 47", new BigDecimal("10.00"));
        Product p48 = new Product(null, "Product 48", new BigDecimal("10.00"));
        Product p49 = new Product(null, "Product 49", new BigDecimal("10.00"));
        Product p50 = new Product(null, "Product 50", new BigDecimal("10.00"));

        cat1.getProducts().addAll(asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        p12.getCategories().add(cat1);
        p13.getCategories().add(cat1);
        p14.getCategories().add(cat1);
        p15.getCategories().add(cat1);
        p16.getCategories().add(cat1);
        p17.getCategories().add(cat1);
        p18.getCategories().add(cat1);
        p19.getCategories().add(cat1);
        p20.getCategories().add(cat1);
        p21.getCategories().add(cat1);
        p22.getCategories().add(cat1);
        p23.getCategories().add(cat1);
        p24.getCategories().add(cat1);
        p25.getCategories().add(cat1);
        p26.getCategories().add(cat1);
        p27.getCategories().add(cat1);
        p28.getCategories().add(cat1);
        p29.getCategories().add(cat1);
        p30.getCategories().add(cat1);
        p31.getCategories().add(cat1);
        p32.getCategories().add(cat1);
        p33.getCategories().add(cat1);
        p34.getCategories().add(cat1);
        p35.getCategories().add(cat1);
        p36.getCategories().add(cat1);
        p37.getCategories().add(cat1);
        p38.getCategories().add(cat1);
        p39.getCategories().add(cat1);
        p40.getCategories().add(cat1);
        p41.getCategories().add(cat1);
        p42.getCategories().add(cat1);
        p43.getCategories().add(cat1);
        p44.getCategories().add(cat1);
        p45.getCategories().add(cat1);
        p46.getCategories().add(cat1);
        p47.getCategories().add(cat1);
        p48.getCategories().add(cat1);
        p49.getCategories().add(cat1);
        p50.getCategories().add(cat1);

        cat1.getProducts().addAll(asList(p1, p2, p3));
        cat2.getProducts().addAll(asList(p2, p4));
        cat3.getProducts().addAll(asList(p5, p6));
        cat4.getProducts().addAll(asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Collections.singletonList(p8));
        cat6.getProducts().addAll(asList(p9, p10));
        cat7.getProducts().addAll(Collections.singletonList(p11));

        p1.getCategories().addAll(asList(cat1, cat4));
        p2.getCategories().addAll(asList(cat1, cat2, cat4));
        p3.getCategories().addAll(asList(cat1, cat4));
        p4.getCategories().addAll(Collections.singletonList(cat2));
        p5.getCategories().addAll(Collections.singletonList(cat3));
        p6.getCategories().addAll(Collections.singletonList(cat3));
        p7.getCategories().addAll(Collections.singletonList(cat4));
        p8.getCategories().addAll(Collections.singletonList(cat5));
        p9.getCategories().addAll(Collections.singletonList(cat6));
        p10.getCategories().addAll(Collections.singletonList(cat6));
        p11.getCategories().addAll(Collections.singletonList(cat7));

        categoryRepository.saveAll(asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(
                asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21,
                        p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
                        p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        State est1 = new State(null, "Minas Gerais");
        State est2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlândia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Collections.singletonList(c1));
        est2.getCities().addAll(asList(c2, c3));

        stateRepository.saveAll(asList(est1, est2));
        cityRepository.saveAll(asList(c1, c2, c3));

        Customer cli1 = new Customer(
                null,
                "Jorge",
                "jorge@gmail.com",
                "36378912377",
                CustomerType.INDIVIDUAL
        );

        cli1.getPhones().addAll(asList("27363323", "93838393"));

        Customer cli2 = new Customer(
                null,
                "Maria Costa",
                "maria@gmail.com",
                "31628382740",
                CustomerType.INDIVIDUAL
        );

        cli2.getPhones().addAll(asList("93883321", "34252625"));

        Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        Address e3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

        cli1.getAdresses().addAll(asList(e1, e2));
        cli2.getAdresses().addAll(Collections.singletonList(e3));

        customerRepository.saveAll(asList(cli1, cli2));
        addressRepository.saveAll(asList(e1, e2, e3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Payment pagto1 = new CardPayment(null, PaymentStatus.GONE, ped1, 6);
        ped1.setPayment(pagto1);

        Payment pagto2 = new ExchangePayment(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"),
                null);
        ped2.setPayment(pagto2);

        cli1.getOrders().addAll(asList(ped1, ped2));

        orderRepository.saveAll(asList(ped1, ped2));
        paymentRepository.saveAll(asList(pagto1, pagto2));

        OrderItem ip1 = new OrderItem(ped1, p1, BigDecimal.ZERO, 1, new BigDecimal("2000.00"));
        OrderItem ip2 = new OrderItem(ped1, p3, BigDecimal.ZERO, 2, new BigDecimal("80.00"));
        OrderItem ip3 = new OrderItem(ped2, p2, new BigDecimal("100.00"), 1, new BigDecimal("800.00"));

        ped1.getItems().addAll(asList(ip1, ip2));
        ped2.getItems().addAll(Collections.singletonList(ip3));

        p1.getItems().addAll(Collections.singletonList(ip1));
        p2.getItems().addAll(Collections.singletonList(ip3));
        p3.getItems().addAll(Collections.singletonList(ip2));

        orderItemRepository.saveAll(asList(ip1, ip2, ip3));
    }
}
