package es.iesjandula.proyecto_lince_server.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.iesjandula.proyecto_lince_server.models.AdminChat;

/**
 * @author David Martinez Flores
 * Repositorio para admin chat
 */
@Repository
public interface IAdminChatRepository extends JpaRepository<AdminChat, Long>
{
	
}
