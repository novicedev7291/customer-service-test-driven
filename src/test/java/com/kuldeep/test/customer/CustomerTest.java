package com.kuldeep.test.customer;

import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class CustomerTest {
    private Validator validator;
    @Before
    public void setup(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        this.validator = localValidatorFactoryBean.getValidator();
    }
    @Test
    public void newInstanceWithValidValuesShouldReturnARecord(){
        Customer customer = new Customer(1L, "first", "last", "first@first.com");
        /*assertThat(customer.getId(), Matchers.is((Long)1L));
        assertThat(customer.getFirst(), Matchers.is("first"));*/
        BDDAssertions.then(customer.getLast()).isEqualToIgnoringCase("last");
        BDDAssertions.then(customer.getFirst()).isEqualToIgnoringCase("first");
        BDDAssertions.then(customer.getEmail()).isEqualToIgnoringCase("first@first.com");
        BDDAssertions.then(customer.getId()).isNotNull();
    }

    @Test
    public void newInstanceWithInvalidConstraintsShouldReturnViolations(){
        Customer customer = new Customer(1L, "first", "last", "email");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        BDDAssertions.then(violations).isNotEmpty();
    }
}
