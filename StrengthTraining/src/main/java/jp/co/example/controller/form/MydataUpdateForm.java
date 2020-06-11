package jp.co.example.controller.form;

import javax.validation.constraints.NotNull;

public class MydataUpdateForm {
	@NotNull
	private Integer weight;
	@NotNull
	private Integer height;


	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
}
