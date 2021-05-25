package com.potemkin.i.webservlet;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    CrudHandler crud = new CrudHandler(SinglEntityManager.getEntityManagerFactory());

    public ServCustomer() {
    }

    /**
     * Метод запроса сущности через get запрос по id
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        if (request.getParameterMap().size() == 0) {
            List<Customer> list = new ArrayList<>(crud.getCustomerAll());
            log.trace("Лист ----->" + list.toString());
            JSONArray jsonArray = new JSONArray(list);
            out.println(jsonArray.toString());
        } else if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            JSONObject json = new JSONObject(crud.getCustomer(id));
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        StringBuffer stringBuf = new StringBuffer();

        try (BufferedInputStream buf2 = new BufferedInputStream(request.getInputStream(), 64)) {
            byte[] bytemas = new byte[64];
            while (buf2.available() != 0) {
                bytemas = buf2.readAllBytes();
                stringBuf.append(new String(bytemas));
            }
        } catch (FileNotFoundException e) {
            log.error("Ошибка чтения doPost() {}", e);
        } catch (IOException e) {
            log.error("Ошибка чтения doPost() {}", e);
        }
        log.info("+1---->{}", stringBuf);
        JSONObject json = new JSONObject(stringBuf.toString());
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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        var out = resp.getWriter();
        StringBuffer stringBuf = new StringBuffer();
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            try (BufferedInputStream buf2 = new BufferedInputStream(req.getInputStream(), 64)) {
                byte[] bytemas = new byte[64];
                while (buf2.available() != 0) {
                    bytemas = buf2.readAllBytes();
                    stringBuf.append(new String(bytemas));
                }
            } catch (FileNotFoundException e) {
                log.error("Ошибка чтения doPost() {}", e);
            } catch (IOException e) {
                log.error("Ошибка чтения doPost() {}", e);
            }
            System.out.println("+1---->" + stringBuf);
            JSONObject json = new JSONObject(stringBuf.toString());
            System.out.println("+2---->" + json);
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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        var out = resp.getWriter();
        if (req.getParameterMap().size() == 1) {
            int id = Integer.parseInt(req.getParameter("id"));
            crud.deleteCust(id);
            out.print(resp.SC_OK);
        } else {
            out.print("Некорректный параметр");
        }
    }
}
