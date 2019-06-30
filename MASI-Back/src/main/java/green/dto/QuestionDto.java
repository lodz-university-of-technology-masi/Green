package green.dto;

import com.google.gson.annotations.SerializedName;

public class QuestionDto {


	@SerializedName("type")
	private String type;

	@SerializedName("description")
	private String description;

	@SerializedName("answer")
	private String answer;

	@SerializedName("maxVal")
	private Integer maxVal;

	@SerializedName("minVal")
	private Integer minVal;

	@SerializedName("option1")
	private String option1;

	@SerializedName("option2")
	private String option2;

	@SerializedName("option3")
	private String option3;

	@SerializedName("id")
	private long id;

	public QuestionDto() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	public Integer getMinVal() {
		return minVal;
	}

	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
