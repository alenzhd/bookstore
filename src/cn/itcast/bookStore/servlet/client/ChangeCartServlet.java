package cn.itcast.bookStore.servlet.client;

import java.io.IOException;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.itcast.bookStore.domain.Product;

/**
 * Servlet implementation class ChangeCartServlet
 */
@WebServlet("/changeCart")
public class ChangeCartServlet extends HttpServlet {
	Logger log = Logger.getLogger(ChangeCartServlet.class);
//	log.i
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.得到要修改的数量
		int count = Integer.parseInt(request.getParameter("count"));

		// 3.从session中获取购物车.
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

		Product p = new Product();
		p.setId(id);

		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}

		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}

}