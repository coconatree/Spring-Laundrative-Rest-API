package com.laundrative_v2.app;

import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.repository.customerRepo.CustomerAddressRepo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CustomerDaoTest
{
        @Autowired
        private WebApplicationContext webApplicationContext;
        private MockMvc mockMvc;

        @InjectMocks
        private CustomerDao service;

        @Before
        public void setUp()
        {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        @Mock
        private CustomerAddressRepo addressRepository;

        @Test
        public void findAddresses() throws Exception
        {
            when(addressRepository.findAllByCustomerIdCustom(anyLong())).thenReturn(Collections.emptyList());

            MvcResult result = mockMvc.perform(
                    MockMvcRequestBuilders.get("/customer/address/1")
            ).andReturn();

            System.out.println("Response : " + result.getResponse());

            verify(addressRepository).findAllByCustomerIdCustom(anyLong());
        }
}

