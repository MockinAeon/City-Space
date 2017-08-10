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
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.ReserveAdmin;

@Controller
public class ReserveActionController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "ReserveAction", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		ReserveAdmin reserveAdmin = (ReserveAdmin) session.getAttribute("name");
		ReserveRoleActionDAO reserveRoleActionDAO = new ReserveRoleActionDAO();
		String action = request.getParameter("action");
		if (action.equals("reject")) {
			String reason = request.getParameter("reason");
			String reservationidST = request.getParameter("reservationid");
			int reservationid = Integer.parseInt(reservationidST);
			System.out.println("CreditActionController POST reason:" + reason + " /reservationid:" + reservationid);

			reserveRoleActionDAO.rejectReservation(reserveAdmin, reservationid, reason);

			return "success";
		} else if (action.equals("approve")) {
			String reservationidST = request.getParameter("reservationid");
			int reservationid = Integer.parseInt(reservationidST);
			System.out.println("CreditActionController POST  reservationid:" + reservationid);

			reserveRoleActionDAO.approveReservation(reserveAdmin, reservationid);

			return "success";
		}

		else {
			return "error";

		}

	}

//	@RequestMapping(value = "ReserveAction", method = RequestMethod.GET)
//	protected ModelAndView handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		ReserveAdmin reserveAdmin = (ReserveAdmin) session.getAttribute("name");
//
//		String action = request.getParameter("action");
//		System.out.println("ReserveActionController GET action:" + action);
//		ModelAndView mv = new ModelAndView();
//		if (action.equals("checkrequest")) {
//			GetReservationDAO getReservationDAO = new GetReservationDAO();
//
//			Enterprise enterprise = reserveAdmin.getEnterprisename();
//
//			mv.addObject("reservationlist", getReservationDAO.reserveRoleGetReservation(enterprise));
//			mv.setViewName("ReserveRole");
//		} else if (action.equals("managecredit")) {
//			AccountDAO accountDAO = new AccountDAO();
//
//			mv.addObject("guestlist", accountDAO.getGuest());
//			mv.setViewName("CreditRole-Credit");
//		}
//		return mv;
//	}

	@RequestMapping(value = "reservecheckrequest", method = RequestMethod.GET)
	protected ModelAndView checkrequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		
		
		if(session.getAttribute("userRole").equals("reserve")){
			ReserveAdmin reserveAdmin = (ReserveAdmin) session.getAttribute("name");
			
			GetReservationDAO getReservationDAO = new GetReservationDAO();
			Enterprise enterprise = reserveAdmin.getEnterprisename();
			mv.addObject("reservationlist", getReservationDAO.reserveRoleGetReservation(enterprise));
			mv.setViewName("ReserveRole");

			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}

	@RequestMapping(value = "ReserveRoleAccount", method = RequestMethod.GET)
	protected String CreditRoleAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "ReserveRoleAccount";
	}
}
