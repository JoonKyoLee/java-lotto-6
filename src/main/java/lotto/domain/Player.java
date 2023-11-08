package lotto.domain;

import static lotto.exception.ErrorMessage.PURCHASE_AMOUNT_IS_NEGATIVE_NUMBER;
import static lotto.exception.ErrorMessage.PURCHASE_AMOUNT_IS_LESS_THAN_MINIMUM_AMOUNT;
import static lotto.exception.ErrorMessage.PURCHASE_AMOUNT_IS_NOT_DIVIDED_UNIT_AMOUNT;
import static lotto.constraint.PurchaseAmountConstraint.*;

import lotto.exception.Exception;
import lotto.util.ConvertInput;

public class Player {
    private final int DIVIDED_REMAINDER = 0;
    private final int purchaseAmount;
    private final int gameCount;

    private Player(int purchaseAmount) {
        validate(purchaseAmount);

        this.purchaseAmount = purchaseAmount;
        gameCount = makeGameCount(purchaseAmount);
    }

    public static Player from(int purchaseAmount) {
        return new Player(purchaseAmount);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public int getGameCount() {
        return gameCount;
    }

    private void validate(int convertedInput) {
        validateNegativeNumber(convertedInput);
        validateLessThanMinimumAmount(convertedInput);
        validateDividedByUnitAmount(convertedInput);
    }

    private void validateNegativeNumber(int convertedInput) {
        if (isNegativeNumber(convertedInput)) {
            throw Exception.from(PURCHASE_AMOUNT_IS_NEGATIVE_NUMBER);
        }
    }

    private void validateLessThanMinimumAmount(int convertedInput) {
        if (!isLessThanMinimumAmount(convertedInput)) {
            throw Exception.from(PURCHASE_AMOUNT_IS_LESS_THAN_MINIMUM_AMOUNT);
        }
    }

    private void validateDividedByUnitAmount(int convertedInput) {
        if(!isDividedByUnitAmount(convertedInput)) {
            throw Exception.from(PURCHASE_AMOUNT_IS_NOT_DIVIDED_UNIT_AMOUNT);
        }
    }

    private boolean isNegativeNumber(int convertedInput) {
        return convertedInput < POSITIVE_THRESHOLD.getValue();
    }

    private boolean isLessThanMinimumAmount(int convertedInput) {
        return convertedInput < MINIMUM_PURCHASE_UNIT_AMOUNT.getValue();
    }

    private boolean isDividedByUnitAmount(int convertedInput) {
        return convertedInput % MINIMUM_PURCHASE_UNIT_AMOUNT.getValue() == DIVIDED_REMAINDER;
    }

    private int makeGameCount(int purchaseAmount) {
        return purchaseAmount / MINIMUM_PURCHASE_UNIT_AMOUNT.getValue();
    }
}