package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    @DisplayName("삭제 성공: 질문, 답변한 사람이 같을때")
    public void delete() throws Exception {
        Answer answer = AnswerTest.answerOf(11L, UserTest.JAVAJIGI);
        Answers answers = new Answers();
        answers.add(answer);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.delete(UserTest.JAVAJIGI, deleteHistories);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    @DisplayName("삭제 실패: 다른 사람이 쓴 답변이 존재")
    public void deleteFail() {
        Answer answer = AnswerTest.answerOf(11L, UserTest.JAVAJIGI);
        Answer anotherAnswer = AnswerTest.answerOf(12L, UserTest.SANJIGI);
        Answers answers = new Answers();
        answers.add(answer);
        answers.add(anotherAnswer);

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
