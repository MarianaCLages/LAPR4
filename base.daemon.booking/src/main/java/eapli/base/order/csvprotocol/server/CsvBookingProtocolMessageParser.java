package eapli.base.order.csvprotocol.server;

import java.text.ParseException;


import eapli.base.catalogmanagement.application.SearchCatalogController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.util.Utility;

/**
 * The message parser for the Booking protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */



@Utility
public class CsvBookingProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(CsvBookingProtocolMessageParser.class);

    private static SearchCatalogController controller;

    private CsvBookingProtocolMessageParser() {
        // avoid instantiation
    }

    /**
     * To inject a different controller for testing purposes.
     *
     * @param controller
     */

    /* package */



public static void injectController(final SearchCatalogController controller) {
        synchronized (lock) {
            CsvBookingProtocolMessageParser.controller = controller;
        }
    }

    private static final Object lock = new Object();

    private static SearchCatalogController getController() {
        synchronized (lock) {
            if (CsvBookingProtocolMessageParser.controller != null) {
                return CsvBookingProtocolMessageParser.controller;
            }
        }
        return new SearchCatalogController();
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     *
     * @return
     */



    public static BookingProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        BookingProtocolRequest request = new UnknownRequest(inputLine);

        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;

        /*


        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);
            if ("GET_AVAILABLE_MEALS".equals(tokens[0])) {
                request = parseGetAvailableMeals(inputLine, tokens);
            } else if ("BOOK_A_MEAL".equals(tokens[0])) {
                request = parseBookAMeal(inputLine, tokens);
            }
        } catch (final ParseException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new ErrorInRequest(inputLine, "Unable to parse request");
        }


         */

        return request;
    }

    /*

    private static BookingProtocolRequest parseBookAMeal(final String inputLine, final String[] tokens) {
        BookingProtocolRequest request;
        if (tokens.length != 3) {
            request = new ErrorInRequest(inputLine, "Wrong number of parameters");
        } else if (isStringParam(tokens[1])) {
            request = new ErrorInRequest(inputLine, "meal id must not be inside quotes");
        } else if (!isStringParam(tokens[2])) {
            request = new ErrorInRequest(inputLine, "user id must be inside quotes");
        } else {
            request = new BookAMealRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]),
                    CsvLineMarshaler.unquote(tokens[2]));
        }
        return request;
    }

    private static boolean isStringParam(final String string) {
        return string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"';
    }

    private static BookingProtocolRequest parseGetAvailableMeals(final String inputLine, final String[] tokens) {
        BookingProtocolRequest request;
        if (tokens.length != 3) {
            request = new ErrorInRequest(inputLine, "Wrong number of parameters");
        } else if (!isStringParam(tokens[1]) || !isStringParam(tokens[2])) {
            request = new ErrorInRequest(inputLine, "Both date and meal type must be inside quotes");
        } else {
            request = new GetAvailableMealsRequest(getController(), inputLine, CsvLineMarshaler.unquote(tokens[1]),
                    CsvLineMarshaler.unquote(tokens[2]));
        }
        return request;
    }



     */
}
