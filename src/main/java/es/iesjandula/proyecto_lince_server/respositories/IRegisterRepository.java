package es.iesjandula.proyecto_lince_server.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.proyecto_lince_server.models.Register;

import java.util.List;

/**
 * @author David Martinez Flores
 * Repositorio para los registros
 */
@Repository
public interface IRegisterRepository extends JpaRepository<Register, Long>
{
	// Nos devuelve solo el primer registro ya que solo tenemos uno no tendriamos que tener problema
	// pero por seguriadad , siempre obtendremos el mas reciente y solo el priemero.
	List<Register> findFirst1ByOrderByIdDesc();
}
