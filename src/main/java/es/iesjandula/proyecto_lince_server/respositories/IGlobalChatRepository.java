package es.iesjandula.proyecto_lince_server.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.proyecto_lince_server.models.GlobalChat;
/**
 * @author David Martinez Flores
 * Repositorio para chats globales
 */
@Repository
public interface IGlobalChatRepository extends JpaRepository<GlobalChat, Long>
{
	// Este es especial , nos da los 10 primeros chats , luego los pondremos en una lista invertida
	// obteniendo asi que los usuarios no tengan que estar generando trafico de mas de 10 chats por persona.
	List<GlobalChat> findFirst10ByOrderByIdDesc();
}
