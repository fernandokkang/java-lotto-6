package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 구매_금액_숫자_아닌_문자_예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 최소_구매_금액_예외_테스트() {
        assertSimpleTest(() -> {
            runException("999");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 최대_구매_금액_예외_테스트() {
        assertSimpleTest(() -> {
            runException("100000001");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 금액_단위_예외_테스트() {
        assertSimpleTest(() -> {
            runException("1111");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_숫자_아닌_문자_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,a");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_6개_아닌_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_중복_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,5");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_범위_45초과_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_범위_1미만_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,0");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 보너스_번호_숫자_아닌_문자_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6","a");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }


    //보너스 번호 한개 아닌 경우
    //보너스 번호 1 ~ 45 사이 아닌 경우
    @Test
    void 보너스_번호_범위_45초과_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,43","46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 보너스_번호_범위_1미만_경우_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6","0");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }
    @Test
    void 보너스_번호_당첨_번호_중복_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6","6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 보너스_번호_개수_1개_아님_예외_처리() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6","7,8");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
