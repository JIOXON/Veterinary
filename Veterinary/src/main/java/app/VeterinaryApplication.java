package app;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import app.adapters.inputs.LoginInput;

@SpringBootApplication
public class VeterinaryApplication implements CommandLineRunner {

	@Autowired
    private ListableBeanFactory beanFactory;
    
    @Autowired
    private LoginInput loginInput;

    public void run(String... args) throws Exception {
        System.out.println("Cargando...");
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Cargando dependencias..." + beanName );
        }
    	loginInput.menu();
    }
    public static void main(String[] args) {
        SpringApplication.run(VeterinaryApplication.class, args);
    }
}