package application.utils.functionalinterface;

import javax.servlet.ServletException;
import java.io.IOException;

public interface MyInterfaceToDAO<T> {
    T betweenBeginAndCommitted() throws ServletException, IOException;
}
