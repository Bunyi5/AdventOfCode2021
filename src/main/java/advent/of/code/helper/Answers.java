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
    TASK_7_2_REAL (94862124),

    TASK_8_1_TEST (26),
    TASK_8_2_TEST (61229),
    TASK_8_1_REAL (310),
    TASK_8_2_REAL (915941),

    TASK_9_1_TEST (15),
    TASK_9_2_TEST (1134),
    TASK_9_1_REAL (500),
    TASK_9_2_REAL (970200),

    TASK_10_1_TEST (26397L),
    TASK_10_2_TEST (288957L),
    TASK_10_1_REAL (271245L),
    TASK_10_2_REAL (1685293086L),

    TASK_11_1_TEST (1656),
    TASK_11_2_TEST (195),
    TASK_11_1_REAL (1729),
    TASK_11_2_REAL (237),

    TASK_12_1_TEST (10),
    TASK_12_2_TEST (36),
    TASK_12_1_REAL (5333),
    TASK_12_2_REAL (146553),

    TASK_13_1_TEST (17),
    TASK_13_1_REAL (618),

    TASK_14_1_TEST (1588L),
    TASK_14_2_TEST (2188189693529L),
    TASK_14_1_REAL (2435L),
    TASK_14_2_REAL (2587447599164L),

    TASK_15_1_TEST (40),
    TASK_15_2_TEST (315),
    TASK_15_1_REAL (373),
    TASK_15_2_REAL (2868);

    private final Object answer;

    Answers(Object answer) {
        this.answer = answer;
    }

    public Object getAnswer() {
        return answer;
    }
}
