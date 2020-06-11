package jp.co.example.controller.form;

import javax.validation.constraints.NotBlank;

public class RegistForm {
	@NotBlank
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	private String pass;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
