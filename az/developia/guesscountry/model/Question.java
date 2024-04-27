package az.developia.guesscountry.model;

import javafx.scene.image.Image;

public class Question {
	private Image flag;
	private String answer;

	public Question(Image flag, String answer) {
		this.flag = flag;
		this.answer = answer;
	}

	public Question() {
	}

	public void setFlag(Image flag) {
		this.flag = flag;
	}

	public Image getFlag() {
		return flag;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}
}
