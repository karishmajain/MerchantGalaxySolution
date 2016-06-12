package biz.galaxy.translator.constant;
/**
 * It holds various error messages to be displayed when any unexpected input is
 * obtained. Various custom messages are prepared to display the specific reason
 * of error.
 * 
 * @author karishma.jain
 * @version 1.0
 */
public final class ErrorMessageConstants {

    public static final String UNSUPPORTED_INPUT = "I have no idea what you are talking about";

    public static final String UNIT_NON_EXISTENCE = "The unit doesn's exist.";

    public static final String ELEMENT_NON_EXISTENCE = "The element doessn't exist";

    public static final String REPETITION_NOT_ALLOWED = "The symbols D L V can never be repeated";

    public static final String SUCCESSIVE_FOUR_TIMES_REPETITION_NOT_ALLOWED =
            "The symbols I, X, C and M can not be repeated four times successively.";

    public static final String CONDITION_FOR_FOURTH_OCCURRENCE_AFTER_THREE_SUCCESSION =
            "In successive 3 repetitions, The third and fourth literal must be separated by a smaller roman numeral";

    private ErrorMessageConstants() {
        //
    }
}
