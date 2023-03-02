package com.techacademy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, nullable = false)
	private String name;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer delete_flag;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    public Date created_at;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    public Date updated_at;
}
