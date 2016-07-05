package cn.itcast.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookStore.dao.NoticeDao;
import cn.itcast.bookStore.domain.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	//前台系统，查询最新添加或修改的一条公告
		public Notice getRecentNotice() {
			try {
				return dao.getRecentNotice();
			} catch (SQLException e) {
				throw new RuntimeException("查询最新添加或修改的一条公告失败！");
			}
		}
		//后台系统，查询所有公告
	public List<Notice> getAllNotices() {
		try {
			return dao.getAllNotices();
		} catch (SQLException e) {
			throw new RuntimeException("查询所有的公告失败！");
		
		}
	}
			//后台系统，添加公告
			public void addNotice(Notice notice) {
				try {
					dao.addNotice(notice);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("添加公告失败!");
				}
			}
}
