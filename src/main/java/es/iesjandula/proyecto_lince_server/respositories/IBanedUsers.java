package es.iesjandula.proyecto_lince_server.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.proyecto_lince_server.models.BanedUsers;

/**
 * @author David Martinez Flores
 * Repositorio para usuarios baneados
 */
@Repository
public interface IBanedUsers extends JpaRepository<BanedUsers, Long>
{
	/**
	 * Method findByEmail
	 * @param email
	 * @return
	 */
	BanedUsers findByEmail(String email);
}
