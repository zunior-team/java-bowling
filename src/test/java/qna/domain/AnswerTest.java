package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 성공")
    public void delete() throws Exception {
        Answer answer = answerOf(11L, UserTest.JAVAJIGI);

        answer.delete(UserTest.JAVAJIGI, new ArrayList<>());

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 실패: 다른 유저가 단 답변")
    public void deleteFail() {
        Answer answer = answerOf(11L, UserTest.JAVAJIGI);

        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class);
    }

    public static Answer answerOf(long id, User user) {
        return new Answer(id, user, QuestionTest.Q1, "Answers Contents");
    }
}
