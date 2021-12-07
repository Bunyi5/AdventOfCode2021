package advent.of.code.helper;

public enum Answers {
    TASK_1_1_TEST (7),
    TASK_1_2_TEST (5),
    TASK_1_1_REAL (1791),
    TASK_1_2_REAL (1822),

    TASK_2_1_TEST (150),
    TASK_2_2_TEST (900),
    TASK_2_1_REAL (1728414),
    TASK_2_2_REAL (1765720035),

    TASK_3_1_TEST (198),
    TASK_3_2_TEST (230),
    TASK_3_1_REAL (3309596),
    TASK_3_2_REAL (2981085),

    TASK_4_1_TEST (4512),
    TASK_4_2_TEST (1924),
    TASK_4_1_REAL (51776),
    TASK_4_2_REAL (16830),

    TASK_5_1_TEST (5),
    TASK_5_2_TEST (12),
    TASK_5_1_REAL (4728),
    TASK_5_2_REAL (17717),

    TASK_6_1_TEST (5934L),
    TASK_6_2_TEST (26984457539L),
    TASK_6_1_REAL (350605L),
    TASK_6_2_REAL (1592778185024L),

    TASK_7_1_TEST (37),
    TASK_7_2_TEST (168),
    TASK_7_1_REAL (344138),
    TASK_7_2_REAL (94862124);

    private final Object answer;

    Answers(Object answer) {
        this.answer = answer;
    }

    public Object getAnswer() {
        return answer;
    }
}
