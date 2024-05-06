package es.iesjandula.proyecto_lince_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import es.iesjandula.proyecto_lince_server.respositories.IAdminChatRepository;
import es.iesjandula.proyecto_lince_server.respositories.IAdminUsersRepository;
import es.iesjandula.proyecto_lince_server.respositories.IGlobalChatRepository;


/**
 * @author David Martinez Flores
 * Clase que arranca el proyecto Spring Boot
 */

// --- ANOTACIONES ---
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan(basePackages = "es.iesjandula.proyecto_lince_server")
@EnableScheduling
@ComponentScan(basePackages = {"es.iesjandula"})
public class ProyectoLinceServerApplication implements CommandLineRunner
{

	/** Attribute adminUsersRepository*/
	@Autowired
	public IAdminUsersRepository adminUsersRepository;
	
	/** Attribute adminUsersRepository*/
	@Autowired
	public IGlobalChatRepository globalChatRepository;
	
	/** Attribute adminUsersRepository*/
	@Autowired
	public IAdminChatRepository adminChatRepository;

	/**
	 * Method main
	 * @param args
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ProyectoLinceServerApplication.class, args);
	}

	/**
	 * Method run
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception
	{
//		// --- INICIALIZAMOS USUARIOS ADMINS ---		
//		AdminUsers admin3 = new AdminUsers();
//		admin3.setEmail("paco.benitez.chico@gmail.com");
//		this.adminUsersRepository.save(admin3);
//		this.adminUsersRepository.flush();
//		
//		// --- INICIALIZAMOS ADMIN CHATS PARA QUE NO ESTE VACIO AL EMPEZAR ---
//		AdminChat adminChat = new AdminChat();
//		adminChat.setText("--- ADMIN CHAT LOADED SUCCESS ---");
//		this.adminChatRepository.save(adminChat);
//		this.adminChatRepository.flush();
//		
//		// --- INICIALIZAMOS CHATS GLOBALES PARA QUE NO ESTE VACIO AL EMPEZAR ---
//		GlobalChat globalChat = new GlobalChat();
//		globalChat.setText("--- GLOBAL CHAT LOADED SUCCESS ---");
//		this.globalChatRepository.save(globalChat);
//		this.globalChatRepository.flush();
	}

}
