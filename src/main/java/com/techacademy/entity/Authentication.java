package com.techacademy.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
    /** ログインユーザ名 */

	public static enum Role {
        一般, 管理者
    }
    @Id
    private String code;

    /** パスワード */
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /** ユーザID */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}
