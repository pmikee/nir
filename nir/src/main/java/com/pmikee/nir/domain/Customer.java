package com.pmikee.nir.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	String id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String address;

	protected Customer() {
	}

	public Customer(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "(" + id + ") " + name + ", " + address;
	}

}
