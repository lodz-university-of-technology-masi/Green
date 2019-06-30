package green.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VersionDto {


	@SerializedName("language")
	private String language;

	@SerializedName("questions")
	private List<QuestionDto> questions;

	@SerializedName("id")
	private int id;

	public VersionDto() {
	}

	public VersionDto(String name, List<QuestionDto> questions) {
		this.language = name;
		this.questions = questions;
	}

	public VersionDto(String name, List<QuestionDto> questions, int id) {
		this.language = name;
		this.questions = questions;
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<QuestionDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
