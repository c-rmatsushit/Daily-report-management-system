package com.techacademy.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, nullable = false)
	private String name;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deleteFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date updatedAt;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private Authentication authentication;

	@PreRemove
    @Transactional
    private void preRemove() {
        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }
}