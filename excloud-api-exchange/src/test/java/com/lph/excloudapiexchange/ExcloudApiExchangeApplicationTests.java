package com.lph.excloudapiexchange;

import com.lph.excloudapiexchange.entity.User;
import com.lph.excloudapiexchange.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcloudApiExchangeApplicationTests {

	@Autowired
	IUserService userService;

	@Test
	public void contextLoads() {
	}

}
