package com.wxr.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxr.spring.dao.AccountDAO;
import com.wxr.spring.dao.GetAccountDAO;
import com.wxr.spring.dao.GetReservationDAO;
import com.wxr.spring.dao.GuestDAO;
import com.wxr.spring.dao.GuestActionDAO;
import com.wxr.spring.dao.GetSpaceDAO;
import com.wxr.spring.pojo.Account;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.Reservation;
import com.wxr.spring.pojo.ReserveAdmin;
@Controller
@RequestMapping("/welcome*.htm")
public class LogController {

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		GuestActionDAO spaceActionDAO = new GuestActionDAO();
		ModelAndView mv = new ModelAndView();
		System.out.println("logcontroller action:"+action);
		if (action.equalsIgnoreCase("login")) {
			GetReservationDAO getReservationDAO = new GetReservationDAO();
			Account account = (Account) session.getAttribute("useraccount");
			long accountid=account.getAccountid();
			if (account.getRole().equals("guest")) {
				
				GuestDAO guestDAO = new GuestDAO();
				Guest guest = guestDAO.getGuestByAccountId(accountid);
				
//set guest in session
				session.setAttribute("name", guest);
				mv.addObject("subscriptionlist", spaceActionDAO.getSubscription(guest));
				mv.addObject("reservationlist", spaceActionDAO.getReservation(guest));
				System.out.println("logcontroller Go to guest role");
				mv.setViewName("Guest-Home");

			} else if (account.getRole().equals("credit")) {
				
				//set requestScope name & all reservation under this enterprise
				GetAccountDAO getAccountDAO = new GetAccountDAO();
				CreditAdmin creditAdmin = getAccountDAO.getCreditByAccountId(accountid);
				Enterprise enterprise = creditAdmin.getEnterprisename();
				
				session.setAttribute("name", creditAdmin);
				
				mv.addObject("reservationlist",getReservationDAO.creditRoleGetReservation(enterprise));
				mv.setViewName("CreditRole");
				
				
			} else if (account.getRole().equals("reserve")) {
				GetAccountDAO getAccountDAO = new GetAccountDAO();
				ReserveAdmin reserveAdmin = getAccountDAO.getReserveByAccountId(accountid);
				Enterprise enterprise = reserveAdmin.getEnterprisename();
				session.setAttribute("name", reserveAdmin);
				mv.addObject("reservationlist",getReservationDAO.reserveRoleGetReservation(enterprise));
				
				mv.setViewName("ReserveRole");
			} else if (account.getRole().equals("sysadmin")) {
				
				session.setAttribute("name","System Manager");
				System.out.println("logcontroller Go to sysadmin role");
				GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
				AccountDAO accountDAO = new AccountDAO();
				//sysadmin login set all info in session.
				//set city
				session.setAttribute("citylist", setSpaceDAO.getCityList());
				
				//set enterprise
				session.setAttribute("enterpriselist", setSpaceDAO.getEnterpriseList());
				
				//set building
				session.setAttribute("buildinglist", setSpaceDAO.getBuildingList());
				
				//set floor
				session.setAttribute("floorlist", setSpaceDAO.getFloorList());
				
				//set Space
				session.setAttribute("spacelist", setSpaceDAO.getSpaceList());
				
				
				//set all reserve admin
				session.setAttribute("ralist", accountDAO.getReserveAdmin());
				//set all credit admin
				session.setAttribute("creditlist", accountDAO.getCreditAdmin());
				//set all guest
				session.setAttribute("guestlist", accountDAO.getGuest());
				
				
				mv.setViewName("Sysadmin");
			}

		} else if (action.equalsIgnoreCase("logout")) {
			session.invalidate();
			mv.setViewName("login");
			
		}
		return mv;

	}

//	@RequestMapping(method = RequestMethod.GET)
//	protected ModelAndView handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String action = request.getParameter("action");
//
//		ModelAndView mv = new ModelAndView();
//		if (action.equalsIgnoreCase("CreateAccount")) {
//			mv.setViewName("CreateAccount");
//		}
//		return mv;
//	}

}
