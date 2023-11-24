package pl.cezarysanecki.voting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import pl.cezarysanecki.voting.model.Question;
import pl.cezarysanecki.voting.model.QuestionAnswer;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.repository.VoteQuestionnaireRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class VotingSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(VotingSpringApplication.class, args);
  }

  @Bean
  @Profile("local")
  CommandLineRunner init(VoteQuestionnaireRepository voteQuestionnaireRepository) {
    return args -> {
      voteQuestionnaireRepository.deleteAll();

      Question firstQuestion = question("Do you like pizza?");

      VoteQuestionnaire firstVoteQuestionnaire = new VoteQuestionnaire();
      firstVoteQuestionnaire.setReadyToVote(false);
      firstVoteQuestionnaire.setCreationDateTime(LocalDateTime.now());
      firstVoteQuestionnaire.setQuestions(List.of(firstQuestion));
      firstQuestion.setQuestionnaire(firstVoteQuestionnaire);

      Question secondquestion = question("Do you have time at 12:00?");

      VoteQuestionnaire secondVoteQuestionnaire = new VoteQuestionnaire();
      secondVoteQuestionnaire.setReadyToVote(true);
      secondVoteQuestionnaire.setCreationDateTime(LocalDateTime.now());
      secondVoteQuestionnaire.setVotingExpiryDateTime(LocalDateTime.now().plusDays(2));
      secondVoteQuestionnaire.setQuestions(List.of(secondquestion));
      secondquestion.setQuestionnaire(secondVoteQuestionnaire);

      voteQuestionnaireRepository.saveAll(List.of(firstVoteQuestionnaire, secondVoteQuestionnaire));
    };
  }

  private static Question question(String questionText) {
    List<QuestionAnswer> answers = yesNoAnswers();

    Question question = new Question();
    question.setText(questionText);
    question.setAnswers(answers);
    answers.forEach(answer -> answer.setQuestion(question));
    return question;
  }

  private static List<QuestionAnswer> yesNoAnswers() {
    QuestionAnswer yesQuestionAnswer = new QuestionAnswer();
    yesQuestionAnswer.setValue("YES");
    QuestionAnswer noQuestionAnswer = new QuestionAnswer();
    noQuestionAnswer.setValue("NO");
    return List.of(yesQuestionAnswer, noQuestionAnswer);
  }

}
