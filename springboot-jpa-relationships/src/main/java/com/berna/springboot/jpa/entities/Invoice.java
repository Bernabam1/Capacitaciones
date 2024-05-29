package com.berna.springboot.jpa.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Long total;

	@ManyToOne // Muchas facturas - un cliente
	@JoinColumn(name = "id_client_example") // Especificar el nombre la FK
	// Esto dice que la factura es el dueño de la relación. Acá va la FK
	private Client client;

	public Invoice() {
	}

	public Invoice(String description, Long total) {
		this.description = description;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Invoice {id=" + id + ", description=" + description + ", total=" + total
				+ /* ", client=" + client + se boora esto para que no se loopee */ "}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(total, other.total);
	}

}
