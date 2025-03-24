package es.iesjandula.proyecto_lince_server.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.proyecto_lince_server.models.AdminChat;
import es.iesjandula.proyecto_lince_server.models.AdminUsers;
import es.iesjandula.proyecto_lince_server.models.BanedUsers;
import es.iesjandula.proyecto_lince_server.models.GlobalChat;
import es.iesjandula.proyecto_lince_server.models.Register;
import es.iesjandula.proyecto_lince_server.respositories.IAdminChatRepository;
import es.iesjandula.proyecto_lince_server.respositories.IAdminUsersRepository;
import es.iesjandula.proyecto_lince_server.respositories.IBanedUsers;
import es.iesjandula.proyecto_lince_server.respositories.IGlobalChatRepository;
import es.iesjandula.proyecto_lince_server.respositories.IRegisterRepository;

/**
 * @author David Martinez
 * Clase api-rest que contiene los endpoints del servidor que seran atacados desde el cliente
 */
@RestController
@RequestMapping("/proyectolince")
@CrossOrigin(origins = "*")
public class ProyectoLinceRest
{

	/** Attribute adminChatRepository*/
	@Autowired
	private IBanedUsers banedUsersRepository;
	
	/** Attribute adminChatRepository*/
	@Autowired
	private IRegisterRepository registerRepository;
	
	/** Attribute adminUsersRepository*/
	@Autowired
	private IAdminUsersRepository adminUsersRepository;

	/** Attribute globalGhatRepository*/
	@Autowired
	private IGlobalChatRepository globalChatRepository;

	/** Attribute adminChatRepository*/
	@Autowired
	private IAdminChatRepository adminChatRepository;

	/**
	 * Method getAdminUser , devuelve la lista de admins
	 * @return
	 */
	@RequestMapping(value = "/get/admins", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAdminUser()
	{
		List<AdminUsers> userList = this.adminUsersRepository.findAll();
		return ResponseEntity.ok(userList);
	}
	
	/**
	 * Method getBanedUsers, devuelve la lista de baneados
	 * @return
	 */
	@RequestMapping(value = "/get/banedusers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getBanedUsers()
	{
		List<BanedUsers> userList = this.banedUsersRepository.findAll();
		return ResponseEntity.ok(userList);
	}

	/**
	 * Method sendGlobalChat , recibe un chat global y lo guarda
	 * @param chatBubble
	 * @return
	 */
	@RequestMapping(value = "/send/globalchat", method = RequestMethod.POST)
	public ResponseEntity<?> sendGlobalChat(@RequestParam String chatBubble)
	{
		GlobalChat chat = new GlobalChat();
		chat.setText(chatBubble);
		this.globalChatRepository.save(chat);
		this.globalChatRepository.flush();
		return ResponseEntity.ok().build();
	}

	/**
	 * Method sendAdminChat, recibe un admin chat y lo guarda
	 * @param chatBubble
	 * @return
	 */
	@RequestMapping(value = "/send/adminchat", method = RequestMethod.POST)
	public ResponseEntity<?> sendAdminChat(@RequestParam String chatBubble)
	{
		AdminChat chat = new AdminChat();
		chat.setText(chatBubble);
		this.adminChatRepository.save(chat);
		this.adminChatRepository.flush();
		return ResponseEntity.ok().build();
	}

	/**
	 * Method getGlobalChat, devuelve solo los 10 primeros registros del chat global
	 * @return
	 */
	@RequestMapping(value = "/get/globalchat", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getGlobalChat()
	{
		List<GlobalChat> list = this.globalChatRepository.findFirst10ByOrderByIdDesc();
		Collections.reverse(list); // INVERTIMOS LA LISTA PARA OBTENER UN ORDEN CORRECTO
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Method getAdminChat, devuelve todos los chats de admin chat
	 * @return
	 */
	@RequestMapping(value = "/get/adminchat", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAdminChat()
	{
		return ResponseEntity.ok().body(this.adminChatRepository.findAll());
	}

	/**
	 * Method getSheetInfo , devuelve la lista de registros
	 * @return
	 */
	@RequestMapping(value = "/get/sheetinfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Register>> getSheetInfo()
	{
		List<Register> list = new ArrayList<Register>();
		list = this.registerRepository.findFirst1ByOrderByIdDesc();
		
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Method resetGlobalChat,resetea el chat glboal
	 * @return
	 */
	@RequestMapping(value = "/reset/globalchat", method = RequestMethod.POST)
	public ResponseEntity<?> resetGlobalChat()
	{
		this.globalChatRepository.deleteAll();		

		GlobalChat globalChat = new GlobalChat();
		globalChat.setText("--- GLOBAL CHAT LOADED SUCCESS ---");
		this.globalChatRepository.save(globalChat);
		this.globalChatRepository.flush();

		return ResponseEntity.ok().build();
	}
	
	/**
	 * Method resetAdminChat, resetea el admin chat
	 * @return
	 */
	@RequestMapping(value = "/reset/adminchat", method = RequestMethod.POST)
	public ResponseEntity<?> resetAdminChat()
	{	
		this.adminChatRepository.deleteAll();
		
		AdminChat adminChat = new AdminChat();
		adminChat.setText("--- ADMIN CHAT LOADED SUCCESS ---");
		this.adminChatRepository.save(adminChat);
		this.adminChatRepository.flush();

		return ResponseEntity.ok().build();
	}
	
	/**
	 * Method resetAdminChat, recibe un email y lo banea (Lo introduce en usuarios baneados)
	 * @return
	 */
	@RequestMapping(value = "/ban/user", method = RequestMethod.POST)
	public ResponseEntity<?> banUser(
			@RequestParam(name = "email",required = true) String email)
	{	
		BanedUsers banedUser = this.banedUsersRepository.findByEmail(email);
		if(banedUser==null) 
		{
			banedUser = new BanedUsers();
			banedUser.setEmail(email);
			this.banedUsersRepository.save(banedUser);
		}
		
		this.banedUsersRepository.flush();

		return ResponseEntity.ok().build();
	}
	
	/**
	 * Method resetAdminChat, desbanea un email (Borra de baneados el email que se le pasa)
	 * @return
	 */
	@RequestMapping(value = "/unban/user", method = RequestMethod.POST)
	public ResponseEntity<?> unBanUser(
			@RequestParam(name = "email",required = true) String email)
	{	
		BanedUsers banedUser = this.banedUsersRepository.findByEmail(email);
		if(banedUser!=null) 
		{
			this.banedUsersRepository.delete(banedUser);
		}
		this.banedUsersRepository.flush();
		return ResponseEntity.ok().build();
	}
}
