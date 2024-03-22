package es.iesjandula.proyecto_lince_server.models;

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
 * Clase para almacenar aquellos usuarios que son administradores
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "admin_users")
public class AdminUsers 
{
	
	/** Attribute id*/
	@GeneratedValue
	@Id
	private Long id;
	
	/** Attribute email*/
	@Column
	private String email;
}
