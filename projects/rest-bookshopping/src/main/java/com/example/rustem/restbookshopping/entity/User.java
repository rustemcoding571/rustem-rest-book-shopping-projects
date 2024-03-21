package com.example.rustem.restbookshopping.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

	@Id
	private String username;
	
	private String password;
	
	private Integer enabled;
}
