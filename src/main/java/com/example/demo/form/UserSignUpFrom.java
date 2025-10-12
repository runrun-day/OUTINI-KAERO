package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserSignUpFrom {

	@NotNull(message="入力してください。")
	private String userName;
	@NotNull(message="入力してください。")
	private String mail;
	@NotBlank(message = "パスワードを入力してください")
	@Size(min=4, max=8, message="4文字から8文字で入力してください")
	private String password;
	@NotBlank(message = "パスワードを入力してください")
	@Size(min=4, max=8, message="4文字から8文字で入力してください")
	private String password2;
}
