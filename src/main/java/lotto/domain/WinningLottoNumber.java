package lotto.domain;

import static lotto.exception.ErrorMessage.BONUS_NUMBER_IS_ALREADY_IN_LOTTO_NUMBERS;
import static lotto.exception.ErrorMessage.BONUS_NUMBER_IS_OUT_OF_RANGE;
import static lotto.constraint.LottoConstraint.LOTTO_MINIMUM_BOUND;
import static lotto.constraint.LottoConstraint.LOTTO_MAXIMUM_BOUND;

import java.util.function.Predicate;
import lotto.exception.Exception;

public class WinningLottoNumber {

    private final Lotto winLottoNumber;
    private final int bonusNumber;

    private WinningLottoNumber(Lotto winLottoNumber, int bonusNumber) {
        this.winLottoNumber = winLottoNumber;
        validate(winLottoNumber, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoNumber of(Lotto winLottoNumber, int bonusNumber) {
        return new WinningLottoNumber(winLottoNumber, bonusNumber);
    }

    private void validate(Lotto winLottoNumber, int bonusNumber) {
        validateBonusNumberInRange(bonusNumber);
        validateBonusNumberInLotto(winLottoNumber, bonusNumber);
    }

    private void validateBonusNumberInRange(int bonusNumber) {
        if (!bonusNumberIsInRange(bonusNumber)) {
            throw Exception.from(BONUS_NUMBER_IS_OUT_OF_RANGE);
        }
    }

    private void validateBonusNumberInLotto(Lotto winLottoNumber, int bonusNumber) {
        if (winLottoNumber.containsBonusNumber(bonusNumber)) {
            throw Exception.from(BONUS_NUMBER_IS_ALREADY_IN_LOTTO_NUMBERS);
        }
    }

    private boolean bonusNumberIsInRange(int bonusNumber) {
        return bonusNumber >= LOTTO_MINIMUM_BOUND.getValue() && bonusNumber <= LOTTO_MAXIMUM_BOUND.getValue();
    }

    public int calculateRank(Lotto otherLotto) {
        int matchNumbers = winLottoNumber.getNumbers()
                .stream()
                .filter(number -> otherLotto.getNumbers()
                        .stream()
                        .anyMatch(Predicate.isEqual(number)))
                .toList()
                .size();

        if (matchNumbers == 5) {

        }
    }

    private int matchWithLotto(Lotto otherLotto) {

    }

    private int matchWithBonus(Lotto otherLotto) {

    }
}