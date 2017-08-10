package com.wxr.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wxr.spring.dao.AccountDAO;
import com.wxr.spring.dao.GetReservationDAO;
import com.wxr.spring.dao.ReserveRoleActionDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Account;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.ReserveAdmin;

@Controller
public class ChangePasswordController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("useraccount");
		String password = request.getParameter("password");
		System.out.println("Changepassword - account:"+account.getUsername());
		System.out.println("Changepassword - password:"+ password);
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.changePassword(account.getAccountid(), password);
		
		return"success";
	}

}
