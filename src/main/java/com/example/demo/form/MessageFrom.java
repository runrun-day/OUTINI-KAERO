package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MessageFrom {

	private Integer catId;
	@NotEmpty(message="入力してください")
	private String title;
	private String pic;
	@NotEmpty(message="入力してください")
	private String explanation;
}
