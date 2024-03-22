package es.iesjandula.proyecto_lince_server.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.proyecto_lince_server.models.AdminUsers;

/**
 * @author David Martinez Flores
 * Repositorio para admin users
 */
@Repository
public interface IAdminUsersRepository extends JpaRepository<AdminUsers, Long>
{
	/**
	 * Method findByEmail
	 * @param email
	 * @return
	 */
	AdminUsers findByEmail(String email);
}
