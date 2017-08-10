package com.wxr.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxr.spring.dao.AccountDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Account;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "login";
	}

	@RequestMapping(value = "ajaxlogin", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountDAO accountDAO = new AccountDAO();
		
		Account account = accountDAO.checkAccount(username);
		if (account == null) {
			if(username.equals("sysadmin")){
				accountDAO.Sysadmin();
			}
			return "error";
		} else {
			String realpassword = account.getPassword();
			if (password.equals(realpassword)) {
				session.setAttribute("useraccount", account);
				session.setAttribute("userRole", account.getRole());
				return "success";
			} else {

				return "error";
			}
		}

	}
}
