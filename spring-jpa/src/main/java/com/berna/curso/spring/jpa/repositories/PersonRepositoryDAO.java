package com.berna.curso.spring.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.berna.curso.spring.jpa.PersonDTO;
import com.berna.curso.spring.jpa.entities.Person;

// Clase asociada y el tipo de dato de la PK
// Al heredar de CrudRepository ya es un componente de spring y se puede inyectar
public interface PersonRepositoryDAO extends CrudRepository<Person, Long> {
// Crud repository tiene un montón de metodos de consulta básicos

	@Query("select p.name from Person p where p.id =?1")
	String getNameById(Long id);

	@Query("select concat(p.name, ' ', p.lastname) as full_name from Person p where p.id =?1")
	String getFullNameById(Long id);

	@Query("select p from Person p where p.id = ?1")
	Optional<Person> findOne(Long id);

	@Query("select p from Person p where p.name ilike %?1%")
	Optional<Person> findOneLikeName(String name);
	// Se puede hacer por convencion findByNameContaining(String name);

	List<Person> findByProgrammingLanguage(String programmingLanguage);

	@Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
	// Nombre de la entidad, no de la tabla
	// Nombre del atributo en el where
	// Esto cuando no respeta la convención de nombres
	List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

	// Este cumple con la convención de nombre y es equivalente al de arriba
	List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

	@Query("select p.name, p.programmingLanguage from Person p")
	List<Object[]> obtenerPersonDataList();

	@Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
	Object obtenerPersonDataFullById(Long id);

	@Query("select p, p.programmingLanguage from Person p")
	List<Object[]> findAllMixPerson();
	
	@Query("select new Person(p.name, p.lastname) from Person p order by p.lastname desc") // También puedo usar el constructor con más parametros y pasar null los otros campos
	List<Person> findAllPersonalizedObjectPerson();
	
	@Query("select new com.berna.curso.spring.jpa.PersonDTO(p.name, p.lastname) from Person p") //Si no es entity tengo q colocar la ruta completa
	List<PersonDTO> findAllPersonDTO();
	
	@Query("select p from Person p where p.id between ?1 and ?2")
	List<Person> findAllBetween(Integer num1, Integer num2);
	
	@Query("select count(p) from Person p")
	Long totalPerson();
	
	@Query("select min(p.id) from Person p")
	Long minId();
	
	@Query("select p.name, length(p.name) from Person p")
	List<Object[]> getPersonNameLength(); // En indice 0 va el nombre y en indice 1 el tamaño
	
	@Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
	Object getAllAggregationFunctions();
	
	//Subqueries
	@Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p2.name)) FROM Person p2)")
	List<Object[]> getShorterNames();
	
	@Query("SELECT p from Person p WHERE p.id=(SELECT MAX(p.id) FROM Person p)")
	Optional<Person> getLastRegistration();
	
	@Query("SELECT p from Person p WHERE p.id IN ?1")
	List<Person> getPersonsByIds(List<Long> ids);
}
