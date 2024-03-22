package es.iesjandula.proyecto_lince_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Martinez Flores
 * Clase para almacenar los chats del chat global
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "global_chat")
public class GlobalChat 
{

	/** Attribute id*/
	@GeneratedValue
	@Id
	@JsonIgnore // FOR DONT SHOW ID ON CHAT
	private Long id;
	
	/** Attribute text*/
	@Column
	private String text;
}
