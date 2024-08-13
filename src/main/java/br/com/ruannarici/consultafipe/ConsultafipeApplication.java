package br.com.ruannarici.consultafipe;

import br.com.ruannarici.consultafipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.executar();
	}
}
