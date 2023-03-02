package com.techacademy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
	@Id
	@Column(length = 20, nullable = false)
	private String code;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 10, nullable = false)
	private String role;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    public Date valid_date;

	private Integer employee_id;
}
