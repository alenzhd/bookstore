package cn.itcast.bookStore.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.bookStore.domain.Order;
import cn.itcast.bookStore.domain.OrderItem;
import cn.itcast.bookStore.domain.Product;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.service.OrderService;
import cn.itcast.bookStore.utils.IdUtils;

//生成订单
@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.得到当前用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// 2.从购物车中获取商品信息
		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");

		// 3.将数据封装到订单对象中
		Order order = new Order();

		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		order.setId(IdUtils.getUUID());// 封装订单id
		order.setUser(user);// 封装用户信息到订单.

		for (Product p : cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setBuynum(cart.get(p));
			item.setP(p);
			order.getOrderItems().add(item);
		}

		System.out.println(order);
		// 4.调用service中添加订单操作.
		OrderService service = new OrderService();
		service.addOrder(order);

		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}

}
