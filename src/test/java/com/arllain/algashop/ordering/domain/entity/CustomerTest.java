package com.arllain.algashop.ordering.domain.entity;

import com.arllain.algashop.ordering.domain.exception.CustomerArchivedException;
import com.arllain.algashop.ordering.domain.valueobject.CustomerId;
import com.arllain.algashop.ordering.domain.valueobject.FullName;
import com.arllain.algashop.ordering.domain.valueobject.LoyaltyPoints;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Customer(
                    new CustomerId(),
                    new FullName("John","Doe"),
                    LocalDate.of(1990, 6, 11),
                    "invalid",
                    "478-256-2504",
                    "255-08-2504",
                    false,
                    OffsetDateTime.now()
            );
        });
    }


    @Test
    void given_invalidEmail_whenTryUpdateCustomerEmail_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John","Doe"),
                LocalDate.of(1990, 6, 11),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-2504",
                false,
                OffsetDateTime.now()
            );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            customer.changeEmail("invalid");
        });
    }

    @Test
    void given_unarchivedCustomer_whenArchice_shouldAnonymize() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Anonymous","Anonymous"),
                LocalDate.of(1990, 6, 11),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-2504",
                false,
                OffsetDateTime.now()
        );
        customer.archive();

        Assertions.assertWith(customer,
                c -> Assertions.assertThat(c.fullName()).isEqualTo(new FullName("Anonymous","Anonymous")),
                c -> Assertions.assertThat(c.email()).isNotEqualTo("john.doe@gmail.com"),
                c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
                c -> Assertions.assertThat(c.birthDate()).isNull(),
                c-> Assertions.assertThat(c.isPromotionNotificationsAllowed()).isFalse()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Anonymous","Anonymous"),
                null,
                "anonymous@anonymous.com",
                "000-000-0000",
                "000-00-0000",
                false,
                true,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                new LoyaltyPoints(10)
        );

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archive);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeEmail("email@gmail.com"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changePhone("123-123-1234"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::desablePromotionNotifications);

    }

    @Test
    void given_brandNewCustomer_whenAddLoyaltyPoints_shouldSumPoints() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John","Doe"),
                LocalDate.of(1990, 6, 11),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-2504",
                false,
                OffsetDateTime.now()
        );
        customer.addLoyaltyPoints(new LoyaltyPoints(10));
        customer.addLoyaltyPoints(new LoyaltyPoints(20));

        Assertions.assertThat(customer.loyaltyPoints()).isEqualTo(new LoyaltyPoints(30));
    }

    @Test
    void given_brandNewCustomer_whenAddInvalidLoyaltyPoints_shouldGenerateExcption() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John","Doe"),
                LocalDate.of(1990, 6, 11),
                "john.doe@gmail.com",
                "478-256-2504",
                "255-08-2504",
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(0)));
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(-10)));
    }

    @Test
    void shouldNotAddValue(){
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add(-5));
    }

    @Test
    void shouldNotAddZeroValue(){
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add(0));
    }

}