package com.potemkin.i.webservlet;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	CrudHandler crud = new CrudHandler(SinglEntityManager.getEntityManagerFactory());

	/**
	 * Метод запроса сущности через get запрос по id
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
	    if(request.getParameterMap().containsKey("customerId")) {
	        int id = Integer.parseInt(request.getParameter("customerId"));
	        JSONArray jsonArray = new JSONArray(new ArrayList<Order>(crud.getOrders(id)));
	        out.println(jsonArray.toString());
	    }else if(request.getParameterMap().containsKey("id")) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Order order = crud.getOredr(id);
	        JSONObject json = new JSONObject(order);
	        out.println(json.toString());
	    }
	    out.close();
	}

	/**
	 * Метод создания сущности через post запрос
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    var stringBuf = new StringBuffer();
	    try (BufferedInputStream buf2 = new BufferedInputStream(request.getInputStream(), 64)) {
            byte[] bytemas = new byte[64];
            while (buf2.available() != 0) {
                bytemas = buf2.readAllBytes();
                stringBuf.append(new String(bytemas));
            }
        } catch (FileNotFoundException e) {
            log.error("Ошибка чтения ServOrder doPost() {}", e);
        } catch (IOException e) {
            log.error("Ошибка чтения ServOrder doPost() {}", e);
        }
	    JSONObject json = new JSONObject(stringBuf.toString());
	    int id = json.getInt("customerId");
	    crud.addEntity(crud.parseForOrder(json), id);
	}

	/**
	 * Метод обнавления через put запрос по id
	 * 
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        StringBuffer stringBuf = new StringBuffer();
        if( request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            try (BufferedInputStream buf2 = new BufferedInputStream(request.getInputStream(), 64)) {
                byte[] bytemas = new byte[64];
                while (buf2.available() != 0) {
                    bytemas = buf2.readAllBytes();
                    stringBuf.append(new String(bytemas));
                }
            } catch (FileNotFoundException e) {
                log.error("Ошибка чтения ServOrder doPut() {}", e);
            } catch (IOException e) {
                log.error("Ошибка чтения ServOrder doPut() {}", e);
            }
            JSONObject json = new JSONObject(stringBuf.toString());
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
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        if(request.getParameterMap().size() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            crud.deleteOrder(id);
            out.print(response.SC_OK);
        }else {out.print("Некорректный параметр"); } 
	}
}
