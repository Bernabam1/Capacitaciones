package com.berna.springboot.jpa.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String lastname;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// No puedo tener el cascade en All por que no puedo hacer remove en ManyToMany
	// ya que puede estar asignado
	@JoinTable(name = "tbl_alumnos_cursos", // Mapear a tabla intermedia existente con nombre
			joinColumns = @JoinColumn(name = "alumno_id"), // Acá configuro la FK del entity principal
			inverseJoinColumns = @JoinColumn(name = "curso_id"), // FK de la contraparte
			uniqueConstraints = @UniqueConstraint(columnNames = { "alumno_id", "curso_id" })) // Defino cuales tienen
																								// que tener valores
																								// unicos
	private Set<Course> courses;

	public Student() {
		this.courses = new HashSet<>();
	}

	public Student(String name, String lastname) {
		this(); // para que inicialice la lista
		this.name = name;
		this.lastname = lastname;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
		course.getStudents().remove(this);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", lastname=" + lastname + ", courses=" + courses + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lastname, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(name, other.name);
	}

}
