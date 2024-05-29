package com.berna.springboot.jpa.entities;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String lastname;
	
	// @JoinColumn("id_cliente") // Puedo setear la FK para q no haga una tabla intermedia
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Un cliente - muchas direcciones
	@JoinTable(name = "tlb_clientes_to_direcciones", // Mapear a tabla existente
			joinColumns = @JoinColumn(name = "id_cliente"),
			inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones" }))
	private Set<Address> addresses;
	// Se crea tabla intermedia entre clientes y direcciones con las FK
	// Se elimina con el orphanRemoval para q no quede la referencia en null
	
	//Relacion inversa: en el OneToMany va el mappedBy, en el ManyToOne va el JoinColumn(se define o se carga auto)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client") //client del atributo
	private Set<Invoice> invoices;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy ="client")
	//En la clase padre el mappedBy, en la clase dependiente(hija dueña de la relacion) el joinColumn
	private ClientDetails clientDetails;

	
	public Client() {
		addresses = new HashSet<>();
		invoices = new HashSet<>();
	}

	public Client(String name, String lastname) {
		this();
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public void addInvoice(Invoice invoice) {
		invoices.add(invoice);
		//Tengo q hacer el proceso bidireccional
		invoice.setClient(this);
	}
	

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
		clientDetails.setClient(this); //bidireccional
	}
	
	public void removeClientDetails(ClientDetails clientDetails) {
		clientDetails.setClient(null);
		this.clientDetails = null;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", lastname=" + lastname + ", addresses=" + addresses + ", invoices=" + invoices + ", clientDetails" + clientDetails + "]";
	}

}