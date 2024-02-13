package application.utils.printer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static application.dao.daoClasses.testUtils.MockUtils.getByteArrayOutputStream;

class PrinterTest {

    @Test
    void printNotFoundMessage() {
        ByteArrayOutputStream mockOut = getByteArrayOutputStream();
        Printer.printNotFoundMessage();
        Assert.assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains("404 Not found"));
    }

    @Test
    void printSuccessfulSaveMethodMessage() {
        ByteArrayOutputStream mockOut = getByteArrayOutputStream();
        Printer.printSuccessfulSaveMethodMessage();
        Assert.assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains(" save successful "));
    }

    @Test
    void printSuccessfulDeleteMethodMessage() {
        ByteArrayOutputStream mockOut = getByteArrayOutputStream();
        Printer.printSuccessfulDeleteMethodMessage();
        Assert.assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains("delete successful"));
    }

    @Test
    void printSuccessfulUpdateMethodMessage() {
        ByteArrayOutputStream mockOut = getByteArrayOutputStream();
        Printer.printSuccessfulUpdateMethodMessage();
        Assert.assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains("update successful"));
    }

    @Test
    void printNullPointerMessage() {
        ByteArrayOutputStream mockOut = getByteArrayOutputStream();
        Printer.printNullPointerMessage();
        Assert.assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains("this is null"));
    }

}