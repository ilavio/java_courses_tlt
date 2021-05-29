package com.potemkin.i.webservlet;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.potemkin.i.CrudHandler;
import com.potemkin.i.SinglEntityManager;
import com.potemkin.i.domain.entity.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class ServOrder
 */
@Slf4j
@WebServlet("/Orders")
public class ServOrder extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String TYPE = "application/json";
    private final String CHARACTER = "UTF-8";
    CrudHandler crud = new CrudHandler(SinglEntityManager.getEntityManagerFactory());

    /**
     * Метод установки кодирования исходящего потока
     * 
     * @param response
     * @return PrintWriter
     * @throws IOException
     */
    public PrintWriter setDefaultEncoding(HttpServletResponse response) throws IOException {
        response.setContentType(TYPE);
        response.setCharacterEncoding(CHARACTER);
        return response.getWriter();
    }

    /**
     * Метод получения значений из тела запроса
     * 
     * @param request
     * @return StringBuffer
     */
    public StringBuffer gettingValues(HttpServletRequest request) {
        var stringBuf = new StringBuffer();
        try (var buf2 = new BufferedInputStream(request.getInputStream(), 64)) {
            byte[] bytemas = new byte[64];
            while (buf2.available() != 0) {
                bytemas = buf2.readAllBytes();
                stringBuf.append(new String(bytemas));
            }
        } catch (FileNotFoundException e) {
            log.error("Ошибка чтения gettingValues() {}", e);
        } catch (IOException e) {
            log.error("Ошибка чтения gettingValues() {}", e);
        }
        return stringBuf;
    }

    /**
     * Метод запроса сущности через get запрос по id
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var out = setDefaultEncoding(response);
        if (request.getParameterMap().containsKey("customerId")) {
            int id = Integer.parseInt(request.getParameter("customerId"));
            var jsonArray = new JSONArray(new ArrayList<Order>(crud.getOrders(id)));
            out.println(jsonArray.toString());
        } else if (request.getParameterMap().containsKey("id")) {
            int id = Integer.parseInt(request.getParameter("id"));
            var order = crud.getOredr(id);
            var json = new JSONObject(order);
            out.println(json.toString());
        }
        out.close();
    }

    /**
     * Метод создания сущности через post запрос
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var stringBuf = gettingValues(request);
        var json = new JSONObject(stringBuf.toString());
        int id = json.getInt("customerId");
        crud.addEntity(crud.parseForOrder(json), id);
    }

    /**
     * Метод обнавления через put запрос по id
     * 
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var out = setDefaultEncoding(response);
        var stringBuf = gettingValues(request);
        if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            var json = new JSONObject(stringBuf.toString());
            crud.changeOrder(json, id);
            out.println(json.toString());
        }
        out.close();
    }

    /**
     * Метод удаления через delete запрос по id
     * 
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var out = setDefaultEncoding(response);
        if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            crud.deleteOrder(id);
            out.print(response.SC_OK);
        } else {
            out.print("Некорректный параметр");
        }
    }
}
