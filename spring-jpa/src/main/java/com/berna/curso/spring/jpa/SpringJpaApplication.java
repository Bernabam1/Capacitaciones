package com.berna.curso.spring.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.berna.curso.spring.jpa.entities.Person;
import com.berna.curso.spring.jpa.repositories.PersonRepositoryDAO;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner { // implemento para correr por consola

	@Autowired
	private PersonRepositoryDAO repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// list();
		// findOne();
		// create();
		// update();
		// delete();
		// personalizedQueries2();
		// queriesAgregacion();
		// subQueries();
	}

	// TODOS ESTOS METODOS IRIAN DENTRO DE UNA CLASE SERVICE QUE PODRÍA TENER
	// DISTINTOS REPOSITORIOS COMO ATRIBUTO

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id: ");
		Long id = scanner.nextLong();

		System.out.println("============Consulta de nombre por ID============");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("============Imprime full name por ID============");
		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("============Campos por ID============");
		Object[] personData = (Object[]) repository.obtenerPersonDataFullById(id);
		System.out.println("ID= " + personData[0] + ", nombre= " + personData[1] + ", apellido" + personData[2]
				+ ", lenguajeProg= " + personData[3]);

		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("============Consulta por obj persona y lenguaje============");
		List<Object[]> personRegs = repository.findAllMixPerson();
		personRegs.forEach(reg -> {
			System.out.println("Programming language = " + reg[1] + ", Person = " + reg[0]); // En el 0 tira el toString
																								// del objeto
		});

		System.out.println("============Puebla y devuelve obj entity de una instancia personalizada============");
		List<Person> persons = repository.findAllPersonalizedObjectPerson();
		persons.forEach(person -> System.out.println(person));

		System.out.println("============Puebla y devuelve obj DTO de una clase personalizada============");
		List<PersonDTO> personDto = repository.findAllPersonDTO();
		personDto.forEach(person -> System.out.println(person));

		System.out.println("============Devuelve persona con ID between num1 y num2============");
		List<Person> personsBetween = repository.findAllBetween(2, 5);
		personsBetween.forEach(person -> System.out.println(person));
		// También se puede buscar between letras
		// Hay query method por convención ej: findByIdBetween(Long id1, Long id2);
		// Order by findByIdBetweenOrderByIdDesc(Long id1, Long id2);
	}

	@Transactional(readOnly = true)
	public void queriesAgregacion() {
		System.out.println("============Count personas y min id============");
		Long count = repository.totalPerson();
		Long min = repository.minId();
		System.out.println("Count person= " + count + ", Min id= " + min);

		System.out.println("============Nombre y largo============");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name= " + name + ", length= " + length);
		});

		System.out.println("============Resumen de aggregations============");
		Object[] reg = (Object[]) repository.getAllAggregationFunctions();
		System.out.println(
				"Min= " + reg[0] + ", Max= " + reg[1] + ", Sum= " + reg[2] + ", Avg= " + reg[3] + ", Count= " + reg[4]);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("============Subquery por nombres mas cortos y sus largos============");
		List<Object[]> registers = repository.getShorterNames();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name= " + name + ", length= " + length);
		});
		
		System.out.println("============Obtener el ultimo registro============");
		Optional<Person> last = repository.getLastRegistration();
		last.ifPresent(System.out::println);
		
		System.out.println("============Where In============");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L,2L,5L));
		persons.forEach(person-> System.out.println(person));
		
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		// optionalPerson.ifPresent(person -> {
		if (optionalPerson.isPresent()) {
			Person person = optionalPerson.orElseThrow();
			System.out.println(person); // Muestro los datos actuales
			System.out.println("Ingrese el lenguaje de programación: ");
			String programminLanguage = scanner.next();
			person.setProgrammingLanguage(programminLanguage);

			Person personNew = repository.save(person);
			System.out.println(personNew);
		} else {
			System.out.println("No existe una persona para ese ID" + id);
		}
		scanner.close();
	}

	@Transactional
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		if (optionalPerson.isPresent()) {
			repository.deleteById(id);
			System.out.println("Se borró persona con id = " + id);
			repository.findAll().forEach(System.out::println); // Muestro la lista nueva
		} else {
			System.out.println("No existe una persona para ese ID" + id);
		}
		// Con expresión lambda quedaría:
		// optionalPerson.ifPresentOrElse(person -> repository.delete(person), () ->
		// System.out.println("No existe"));
		scanner.close();
	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre: ");
		String name = scanner.next();
		System.out.println("Apellido: ");
		String lastname = scanner.next();
		System.out.println("Lenguaje: ");
		String programminLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programminLanguage);
		// Si el ID es null lo inserta, si existe le hace update

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
		;
	}

	@Transactional(readOnly = true) // Cuando son select, transactional con readOnly en true
	public void findOne() {
//		Person person = null;
//		Optional<Person> optionalPerson = repository.findById(8L);
//		if(optionalPerson.isPresent()) {
//			person = optionalPerson.get();
//		} else {}
//		System.out.println(person);

		repository.findOneLikeName("Ria").ifPresent(person -> System.out.println(person));
		// También puedo simplificar la expresión ifPresent(System.out::println) porque
		// se pasa el mismo argumento como referencia
	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Maria");

		// Por cada person de persons imprimo
		persons.stream().forEach(person -> {
			System.out.println(person);

		});

		List<Object[]> personData = repository.obtenerPersonDataList();
		personData.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});
	}

}
