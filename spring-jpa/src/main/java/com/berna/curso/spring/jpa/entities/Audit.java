package com.berna.curso.spring.jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {
	
	@Column(name ="created_at")
	private LocalDateTime createdAt;
	@Column(name ="updated_at")
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		System.out.println("Evento del ciclo de vida del Entity: Pre-Persist");
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void PreUpdate() {
		System.out.println("Evento del ciclo de vida del Entity: Pre-Update");
		this.updatedAt = LocalDateTime.now();
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
