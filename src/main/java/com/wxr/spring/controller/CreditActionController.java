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
import com.wxr.spring.dao.CreditRoleActionDAO;
import com.wxr.spring.dao.GetReservationDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Enterprise;

@Controller
public class CreditActionController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "CreditAction", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		CreditRoleActionDAO creditRoleActionDAO = new CreditRoleActionDAO();
		CreditAdmin creditAdmin = (CreditAdmin) session.getAttribute("name");
		if (action.equals("addadvice")) {
			String advice = request.getParameter("advice");
			String reservationidST = request.getParameter("reservationid");
			int reservationid = Integer.parseInt(reservationidST);
			System.out.println("CreditActionController POST advice:" + advice + " /reservationid:" + reservationid);

			creditRoleActionDAO.processReservation(creditAdmin, reservationid, advice);

			return "success";
		} else if (action.equals("addcredit")) {
			String guestidST = request.getParameter("guestid");
			long guestid = Long.parseLong(guestidST);
			System.out.println("CreditActionController POST /guestid:" + guestid);
			creditRoleActionDAO.addGuestCredit(guestid);
			return "success";
		} else if (action.equals("reducecredit")) {
			String guestidST = request.getParameter("guestid");
			long guestid = Long.parseLong(guestidST);
			System.out.println("CreditActionController POST /guestid:" + guestid);
			creditRoleActionDAO.reduceGuestCredit(guestid);
			return "success";
		}

		else {
			return "error";

		}

	}

//	@RequestMapping(value = "CreditAction", method = RequestMethod.GET)
//	protected ModelAndView handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		CreditAdmin creditAdmin = (CreditAdmin) session.getAttribute("name");
//		String action = request.getParameter("action");
//		System.out.println("CreditActionController GET action:" + action);
//		ModelAndView mv = new ModelAndView();
//		if (action.equals("checkrequest")) {
//			GetReservationDAO getReservationDAO = new GetReservationDAO();
//
//			Enterprise enterprise = creditAdmin.getEnterprisename();
//
//			mv.addObject("reservationlist", getReservationDAO.creditRoleGetReservation(enterprise));
//			mv.setViewName("CreditRole");
//		} else if (action.equals("managecredit")) {
//			AccountDAO accountDAO = new AccountDAO();
//
//			mv.addObject("guestlist", accountDAO.getGuest());
//			mv.setViewName("CreditRole-Credit");
//		}
//		return mv;
//	}

	@RequestMapping(value = "checkrequest", method = RequestMethod.GET)
	protected ModelAndView checkrequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("userRole").equals("credit")){
			CreditAdmin creditAdmin = (CreditAdmin) session.getAttribute("name");
			
			GetReservationDAO getReservationDAO = new GetReservationDAO();

			Enterprise enterprise = creditAdmin.getEnterprisename();

			mv.addObject("reservationlist", getReservationDAO.creditRoleGetReservation(enterprise));
			mv.setViewName("CreditRole");

			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
		
	}

	@RequestMapping(value = "creditRolemanagecredit", method = RequestMethod.GET)
	protected ModelAndView managecredit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		AccountDAO accountDAO = new AccountDAO();

		
		HttpSession session = request.getSession();
		if(session.getAttribute("userRole").equals("credit")){
			mv.addObject("guestlist", accountDAO.getGuest());
			mv.setViewName("CreditRole-Credit");
			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}
	
	@RequestMapping(value = "CreditRoleAccount", method = RequestMethod.GET)
	protected String CreditRoleAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "CreditRoleAccount";
	}
}
