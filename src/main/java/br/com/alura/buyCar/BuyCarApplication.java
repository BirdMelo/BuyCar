package br.com.alura.buyCar;

import br.com.alura.buyCar.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuyCarApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BuyCarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main menu = new Main();
		menu.show();
	}
}
