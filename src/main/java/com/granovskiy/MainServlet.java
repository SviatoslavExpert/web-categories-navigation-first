package com.granovskiy;

import com.granovskiy.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.granovskiy.Factory.*;

public class MainServlet extends HttpServlet {

    private static final Map<Request, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put(Request.of("GET", "/servlet/login"), r -> ViewModel.of("login"));
        controllerMap.put(Request.of("POST", "/servlet/login"), getLoginUserController(getUserServiceImpl()));
        controllerMap.put(Request.of("GET", "/servlet/categories"), getGetAllCategoriesController(getCategoryService()));
        controllerMap.put(Request.of("GET", "/servlet/category"), getGetCategoryByIdController(getCategoryService()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = Request.of(req.getMethod(), req.getRequestURI(), req.getParameterMap());
        Controller controller = controllerMap.getOrDefault(request, r -> ViewModel.of("404"));
        ViewModel vm = controller.process(request);
        processViewModel(vm, req, resp);
    }
    /* controller.process() returns an object of class ViewModel, the new object has properties
        of ViewModel class (String view, Map<String, Object> attributes, String REDIRECT_TEMPLATE) */

    private void processViewModel(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //vm.getAttributes().forEach((k, v) -> req.setAttribute(k, v));
        vm.getAttributes().forEach(req::setAttribute);
        req.getRequestDispatcher(vm.getRedirectUri()).forward(req, resp);
    }
    /*  vm.getAttributes().forEach(req::setAttribute);
         - the attributes are taken from vm object map (keys and values),
        those attributes (keys and values) are set into req variable

        req.getRequestDispatcher(vm.getRedirectUri()).forward(req, resp);
         - method getRequestDispatcher works for req object,
        it gets URI from vm object and returns an object of RequestDispatcher type

        forward(req, resp)
        - forwards a request from a servlet to another resource
        (servlet, JSP file, or HTML file) on the server.

        https://docs.oracle.com/cd/E17802_01/products/products/servlet/2.3/javadoc/javax/servlet/RequestDispatcher.html
     */
}
