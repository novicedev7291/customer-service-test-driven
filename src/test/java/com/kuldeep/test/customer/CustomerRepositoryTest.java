package com.kuldeep.test.customer;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager manager;

    @Autowired
    private CustomerRepository respository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveShouldMapCorrectly(){
        Customer customer = new Customer(null, "first", "last", "first@email.com");
        Customer saved = this.manager.persistAndFlush(customer);
        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getFirst()).isEqualToIgnoringCase(customer.getFirst());
        BDDAssertions.then(saved.getLast()).isEqualToIgnoringCase(customer.getLast());
        BDDAssertions.then(saved.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    public void saveShouldMapCorrectlyUsingRepository(){
        Customer customer = new Customer(null, "first", "last", "first@email.com");
        Customer saved = this.respository.save(customer);
        BDDAssertions.then(saved.getId()).isNotNull();
        BDDAssertions.then(saved.getFirst()).isEqualToIgnoringCase(customer.getFirst());
        BDDAssertions.then(saved.getLast()).isEqualToIgnoringCase(customer.getLast());
        BDDAssertions.then(saved.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    public void newObjectWithInvalidParametersShouldResultConstraintViolation(){
        this.expectedException.expect(ConstraintViolationException.class);
        this.respository.saveAndFlush(new Customer(null, null, null, null));
    }
}
