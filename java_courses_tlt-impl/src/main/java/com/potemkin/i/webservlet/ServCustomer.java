package com.potemkin.i.webservlet;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.potemkin.i.CrudHandler;
import com.potemkin.i.SinglEntityManager;
import com.potemkin.i.domain.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/Customers")
public class ServCustomer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String TYPE = "application/json";
    private final String CHARACTER = "UTF-8";
    CrudHandler crud = new CrudHandler(SinglEntityManager.getEntityManagerFactory());

    public ServCustomer() {
    }

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
        if (request.getParameterMap().size() == 0) {
            List<Customer> list = new ArrayList<>(crud.getCustomerAll());
            log.trace("Лист ----->" + list.toString());
            var jsonArray = new JSONArray(list);
            out.println(jsonArray.toString());
        } else if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            var json = new JSONObject(crud.getCustomer(id));
            out.print(json.toString());
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
        var out = setDefaultEncoding(response);
        var stringBuf = gettingValues(request);
        log.info("+1---->{}", stringBuf);
        var json = new JSONObject(stringBuf.toString());
        log.info("+2---->{}", json);
        out.println(json.toString());
        crud.addEntity(crud.parseForCustomer(json));
        out.close();
    }

    /**
     * Метод обнавления через put запрос по id
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = setDefaultEncoding(resp);
        var stringBuf = gettingValues(req);
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            log.info("+1---->" + stringBuf);
            var json = new JSONObject(stringBuf.toString());
            log.info("+2---->" + json);
            out.println(json.toString());
            crud.changeEntity(json, id);
        }
        out.close();
    }

    /**
     * Метод удаления через delete запрос по id
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = setDefaultEncoding(resp);
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            crud.deleteCust(id);
            out.print(resp.SC_OK);
        } else {
            out.print("Некорректный параметр");
        }
    }
}
