package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserSignUpFrom {

	@NotEmpty(message="入力してください")
	private String userName;
	@NotEmpty(message="入力してください")
	@Email(message="有効なメールアドレスを入力してください")
	private String mail;
	@Size(min=4, max=8, message="4文字から8文字で入力してください")
	private String password;
	@Size(min=4, max=8, message="4文字から8文字で入力してください")
	private String password2;
}
