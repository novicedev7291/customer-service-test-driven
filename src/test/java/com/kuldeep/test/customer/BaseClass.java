package com.kuldeep.test.customer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerSvcSimpleTestApplication.class)
public class BaseClass {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRestController customerRestController;

    @Before
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.customerRestController);
        when(this.customerRepository.findById(1L)).thenReturn(
                Optional.of(new Customer(1L, "first", "last", "first@email.com"))
        );
        when(this.customerRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Customer(1L, "first", "last", "first@email.com"),
                        new Customer(2L, "second", "last", "second@email.com")
                )
        );
    }
}
