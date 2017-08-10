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
import com.wxr.spring.dao.GuestDAO;
import com.wxr.spring.dao.ManageSpaceDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Account;
import com.wxr.spring.pojo.Enterprise;

@Controller
public class ManageAccountController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "manageaccount", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ManageSpaceDAO manageSpaceDAO = new ManageSpaceDAO();
		AccountDAO accountDAO = new AccountDAO();
		// add reserve role
		if (action.equals("addreserve")) {
			String enterprisename = request.getParameter("enterpriseselect");
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("enterprisename:" + enterprisename + "/action:" + action + "/fullname:" + fullname
					+ "/username:" + username + "/password:" + password);
			Enterprise enterprise = manageSpaceDAO.checkEnterprise(enterprisename);

			// check exist or not
			Account account = accountDAO.checkAccount(username);
			if (account == null) {
				accountDAO.createReserveAdmin(username, password, fullname, enterprise);
				System.out.println("ManageAccountController: create reserve role success");
				session.setAttribute("ralist", accountDAO.getReserveAdmin());
				return "success";
			} else {
				return "exist";
			}
		} else if (action.equals("addcredit")) {
			String enterprisename = request.getParameter("enterpriseselect");
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("enterprisename:" + enterprisename + "/action:" + action + "/fullname:" + fullname
					+ "/username:" + username + "/password:" + password);
			Enterprise enterprise = manageSpaceDAO.checkEnterprise(enterprisename);

			// check exist or not
			Account account = accountDAO.checkAccount(username);
			if (account == null) {
				accountDAO.createCreditAdmin(username, password, fullname, enterprise);
				System.out.println("ManageAccountController: create credit role success");
				session.setAttribute("creditlist", accountDAO.getCreditAdmin());
				return "success";
			} else {
				return "exist";
			}
		} else if (action.equals("addguest")) {
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("fullname:" + fullname + "/username:" + username + "/password:" + password);

			GuestDAO guestDAO = new GuestDAO();
			Account account = accountDAO.checkAccount(username);
			if (account == null) {
				guestDAO.create(username, password, fullname);
				System.out.println("CreateGuest: create guest success");
				return "success";
			} else {
				return "exist";
			}
		}

		else {
			System.out.println("cityerror");
			return "error";
		}

	}

	// @RequestMapping(value = "", method = RequestMethod.GET)
	// protected ModelAndView handleGetRequest(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// String action = request.getParameter("action");
	// System.out.println("ManageAccountController GET ,action:" + action);
	// ModelAndView mv = new ModelAndView();
	// if (action.equals("reserve")) {
	// mv.setViewName("Sysadmin-reserve");
	// } else if (action.equals("credit")) {
	// mv.setViewName("Sysadmin-credit");
	// } else if (action.equals("guest")) {
	// mv.setViewName("Sysadmin-guest");
	// }
	//
	// return mv;
	// }

	@RequestMapping(value = "managereserve", method = RequestMethod.GET)
	protected String managereserve(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-reserve";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "managecredit", method = RequestMethod.GET)
	protected String managecredit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-credit";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "manageguest", method = RequestMethod.GET)
	protected String manageguest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-guest";
		} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "SysadminRoleAccount", method = RequestMethod.GET)
	protected String CreditRoleAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "SysadminRoleAccount";
	}
}
