package az.developia.guesscountry.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import az.developia.guesscountry.main.Main;
import az.developia.guesscountry.model.Question;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class countryController implements Initializable {
	@FXML
	private Label scoreLabel;
	
	@FXML
	private ImageView questionImageView;

	@FXML
	private Button answer1Button;

	@FXML
	private Button answer2Button;

	@FXML
	private Button answer3Button;

	@FXML
	private Button answer4Button;

	private int score = 0;
	private Question currentQuestion;
	private int currentQuestionIndex;
	private String[] olkeAdlari = { "Azerbaijan", "Turkiye", "Russia", "Sweden", "America", "Norway", "Pakistan",
			"Spain" };
	private ArrayList<Question> questions = new ArrayList<>();

	// private ArrayList olkeAdlariSiyahi = (ArrayList) Arrays.asList(olkeAdlari);
	private void loadAnswers() {
		ArrayList<String> answers = new ArrayList<>();

		while (answers.size() != 3) {
			Integer randomNumber = new Random().nextInt(olkeAdlari.length);
			if (answers.contains(olkeAdlari[randomNumber])) {
              continue;
			} 
            for(int i =0; i<answers.size();i++) {
           	 if (currentQuestion.getAnswer() == answers.get(i)) {
           		 System.out.println(currentQuestion.getAnswer());
           		 System.out.println(answers.get(i));
           		 answers.remove(i);
           	 }
            }
			answers.add(olkeAdlari[randomNumber]);
		}

		answers.add(currentQuestion.getAnswer());

		Collections.shuffle(answers);

		Iterator<String> randomizer = answers.iterator();
		answer1Button.setText(randomizer.next());
		answer2Button.setText(randomizer.next());
		answer3Button.setText(randomizer.next());
		answer4Button.setText(randomizer.next());
	}

	private void clearButtonsStyle() {
		answer1Button.setStyle("-fx-background-color: orange;");
		answer2Button.setStyle("-fx-background-color: orange;");
		answer3Button.setStyle("-fx-background-color: orange;");
		answer4Button.setStyle("-fx-background-color: orange;");
	}

	private void nextQuestion() {
		currentQuestionIndex++;
		if (currentQuestionIndex == questions.size()) {
			Main.mainStage.close();
		} else {
			currentQuestion = questions.get(currentQuestionIndex);
			questionImageView.setImage(currentQuestion.getFlag());
		}
	}

	@FXML
	private void answerButtonClick(ActionEvent event) {
		Button clickedButton = ((Button) event.getTarget());
		String clickedAnswer = ((Button) event.getTarget()).getText();
		if (clickedAnswer.equals(currentQuestion.getAnswer())) {
			score++;
			scoreLabel.setText("Score: "+score);
			System.out.println("Dogrudur");
			clickedButton.setStyle("-fx-background-color: green;");
			Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
				clearButtonsStyle();
				nextQuestion();
				loadAnswers();
			}));
			timeline1.setCycleCount(1);
			timeline1.play();
		} else {
			System.out.println("Yanlisdir");
			clickedButton.setStyle("-fx-background-color: red;");
			Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
				clearButtonsStyle();
			}));
			timeline1.setCycleCount(1);
			timeline1.play();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		questions = new ArrayList();

		Question question1 = new Question();
		Image flag1 = new Image("az/developia/guesscountry/images/Azerbaijan.jpg");
		String answer1 = "Azerbaijan";

		Question question2 = new Question();
		Image flag2 = new Image("az/developia/guesscountry/images/Turkiye.jpg");
		String answer2 = "Turkiye";

		question1.setFlag(flag1);
		question1.setAnswer(answer1);
		question2.setFlag(flag2);
		question2.setAnswer(answer2);

		questions.add(question1);
		questions.add(question2);

		currentQuestionIndex = 0;
		questionImageView.setImage(questions.get(0).getFlag());
		currentQuestion = questions.get(currentQuestionIndex);

		loadAnswers();

	}

}
