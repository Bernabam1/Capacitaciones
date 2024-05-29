package com.berna.curso.spring.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons") // Si no se coloca table la la tabla va a tener el mismo nombre q la clase
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Identity es para SQL
	private Long id;
	
	private String name; // Puedo omitir el nombre del campo si se llama igual en la tabla
	private String lastname;
	
	@Column(name ="programming_language") // A SQL no le gusta el camelCase
	private String programmingLanguage;
	
	@Embedded // Hay una clase embebida
	private Audit audit = new Audit();

	public Person() { // JPA e Hibernate tienen q tener un constructor vacío
		// Si defino un constructor con parámetros, tengo q implementar el constructor vacío
	}
	
	public Person(String name, String lastname) { 
		// Para instancia personalizada de findAllPersonalizedObjectPerson()
		this.name = name;
		this.lastname = lastname;
	}

	public Person(Long id, String name, String lastname, String programmingLanguage) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.programmingLanguage = programmingLanguage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", programmingLanguage="
				+ programmingLanguage + ", createdAt=" + audit.getCreatedAt() + ", updatedAt=" + audit.getUpdatedAt() + "]";
	}
	
}
