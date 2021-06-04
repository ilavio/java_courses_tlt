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

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Servlet implementation class ServSupplier
 */
@Slf4j
@WebServlet("/Supplier")
public class ServSupplier extends HttpServlet {
    private static final long serialVersionUID = 1L;
    SupplierRepository crud = new SupplierRepository(Persistence.createEntityManagerFactory("JPA-First"));

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServSupplier() {
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
            List<Supplier> list = new ArrayList<>(crud.getSupplierAll());
            log.trace("Лист ----->" + list.toString());
            JSONArray jsonArray = new JSONArray(list);
            out.println(jsonArray.toString());
        } else if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            JSONObject json = new JSONObject(crud.getSupplier(id));
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
        crud.addSupplier(crud.parseForSupplier(json));
        out.close();
    }

    /**
     * Метод обнавления через put запрос по id
     * 
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        StringBuffer stringBuf = new StringBuffer();
        if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
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
            crud.changeEntity(json, id);
        }
        out.close();
    }

    /**
     * Метод удаления через delete запрос по id
     * 
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        if (request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            crud.deleteSupplier(id);
            out.print(response.SC_OK);
        } else {
            out.print("Некорректный параметр");
        }
    }
}
