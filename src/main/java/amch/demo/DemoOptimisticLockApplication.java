package amch.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
public class DemoOptimisticLockApplication{

	@Autowired
	private DataService service;
	public static void main(String[] args) {
		SpringApplication.run(DemoOptimisticLockApplication.class, args);
	}

}
