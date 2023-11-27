package com.springlessons.bo;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springlessons.model.Client;
import com.springlessons.model.Sex;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ClientBOTest {
	
	@Autowired
	private ClientBO bo;

	@Test
	@Order(1)
	public void insert() {
		Client client = new Client();
		client.setName("Teste");
		client.setCpf("12345678910");
		client.setBirthdata(LocalDate.of(1999, 4, 1));
		client.setCelphone("11999999999");
		client.setPhone("1133333333");
		client.setSex(Sex.MALE);
		client.setEmail("teste@teste.com");
		client.setStatus(true);
		bo.insert(client);
	}
	
	@Test
	@Order(2)
	public void findById() {
		Client client = bo.findById(1L);
		System.out.println(client);
	}
	
	@Test
	@Order(3)
	public void update() {
		Client client = bo.findById(1L);
		client.setName("Ciclano");
		bo.update(client);
	}
}













