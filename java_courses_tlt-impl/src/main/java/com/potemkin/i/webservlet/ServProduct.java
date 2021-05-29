package com.potemkin.i.webservlet;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.potemkin.i.CrudHandlerSupAndProduct;
import com.potemkin.i.SinglEntityManager;
import com.potemkin.i.domain.entity.Product;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class ServProduct
 */
@Slf4j
@WebServlet("/Products")
public class ServProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String TYPE = "application/json";
    private final String CHARACTER = "UTF-8";
    CrudHandlerSupAndProduct crud = new CrudHandlerSupAndProduct(SinglEntityManager.getEntityManagerFactory());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServProduct() {
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
            List<Product> list = new ArrayList<>(crud.getProductAll());
            log.trace("Лист ----->" + list.toString());
            var jsonArray = new JSONArray(list);
            out.println(jsonArray.toString());
        } else if (request.getParameterMap().containsKey("id")) {
            int id = Integer.parseInt(request.getParameter("id"));
            var prod = crud.getProduct(id);
            var json = new JSONObject(prod);
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
        var out = setDefaultEncoding(response);
        var stringBuf = gettingValues(request);
        var json = new JSONObject(stringBuf.toString());
        log.info("+1---->{}", json);
        out.println(json.toString());
        int id = json.getInt("supplierId");
        crud.addEntity(crud.parseForProduct(json), id);
        out.close();
    }

    /**
     * Метод обнавления через put запрос по id
     * 
     * @see HttpServlet#doPut(HttpServletRequest req, HttpServletResponse resp)
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = setDefaultEncoding(resp);
        var stringBuf = gettingValues(req);
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            var json = new JSONObject(stringBuf.toString());
            crud.changeProduct(json, id);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = setDefaultEncoding(resp);
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            crud.deleteProduct(id);
            out.print(resp.SC_OK);
        } else {
            out.print("Некорректный параметр");
        }
    }
}
