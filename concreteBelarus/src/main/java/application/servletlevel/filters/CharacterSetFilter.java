package application.servletlevel.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static application.utils.constant.ConstantsContainer.FOR_ALL_PAGE;

@WebFilter(filterName = "CharacterSetFilter", urlPatterns = "/*")
public class CharacterSetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
//        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(FOR_ALL_PAGE);
//        requestDispatcher.include(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
