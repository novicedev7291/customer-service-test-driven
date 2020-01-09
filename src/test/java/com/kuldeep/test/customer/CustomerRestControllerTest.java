package com.kuldeep.test.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void customerByIdShouldReturnAJsonCustomer() throws Exception{
        when(this.customerRepository.findById(1L)).thenReturn(
               Optional.of(new Customer(1L, "first", "last", "first@email.com"))
        );

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("@.id").value(1L));
    }

    @Test
    public void customersShouldReturnAllCustomers() throws Exception{
        when(this.customerRepository.findAll()).thenReturn(Arrays.asList(
                new Customer(1L, "first", "last", "first@email.com"),
                new Customer(2L, "first", "last", "second@email.com")
        ));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("@[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@[0].first").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("@[0].last").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("@[0].email").value("first@email.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("@[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("@[1].email").value("second@email.com"));

    }
}
