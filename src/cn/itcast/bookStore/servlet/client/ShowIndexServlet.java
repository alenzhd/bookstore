package cn.itcast.bookStore.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookStore.domain.Notice;
import cn.itcast.bookStore.service.NoticeService;
import cn.itcast.bookStore.service.ProductService;

/**
 *	前台页面展示的servlet 
 *	1、展示最新添加或修改的一条公告
 *  2、展示本周热销商品
 */


/**
 * Servlet implementation class ShowIndexSerlvet
 */
@WebServlet("/ShowIndexServlet")
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询最近一条公告，传递到index.jsp页面进行展示
				NoticeService nService = new NoticeService();
				Notice notice = nService.getRecentNotice();
				request.setAttribute("n", notice);
				//查询本周热销的两条商品，传递到index.jsp页面进行展示
				ProductService pService = new ProductService();
				List<Object[]> pList =  pService.getWeekHotProduct();
				request.setAttribute("pList", pList);				
				//请求转发
				request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

}
