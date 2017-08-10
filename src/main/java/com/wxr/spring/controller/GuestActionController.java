package com.wxr.spring.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wxr.spring.dao.GuestActionDAO;
import com.wxr.spring.dao.GetSpaceDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.Reservation;
import com.wxr.spring.pojo.Space;
import com.wxr.spring.pojo.Subscription;

@Controller
public class GuestActionController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "GuestAction", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		GuestActionDAO guestActionDAO = new GuestActionDAO();
		GetSpaceDAO getSpaceDAO = new GetSpaceDAO();

		Guest guest = (Guest) session.getAttribute("name");
		if (action.equals("usespace")) {
			long spaceid = Long.parseLong(request.getParameter("spaceid"));
			System.out.println("GuestActionController Post spaceid:" + spaceid);
			// change space status, add space into guest.

			Space space = getSpaceDAO.getSpaceById(spaceid);
			System.out.println("GuestActionController Post  GETspaceByID:" + space);

			guestActionDAO.useSpace(guest, space);
			// spaceActionDAO.useSpace2(space);
			// shi fou yao set dao seesion zhong
			System.out.println("GuestActionController Post Guest:" + guest + guest.getUsingSpace());

			return "success";
		} else if (action.equals("leavespace")) {
			Space space = guest.getUsingSpace();
			System.out.println("GuestActionController Post:gonna leave this space:" + space);
			guestActionDAO.leaveSpace(guest, space);
			return "success";
		} else if (action.equals("subscribe")) {
			long spaceid = Long.parseLong(request.getParameter("spaceid"));
			System.out.println("GuestActionController Post spaceid:" + spaceid);
			Space space = getSpaceDAO.getSpaceById(spaceid);
			System.out.println("GuestActionController Post  GETspaceByID:" + space);

			Subscription subscription = guestActionDAO.checkSubcribe(guest, space);
			if (subscription == null) {
				guestActionDAO.createSubcribe(guest, space);
				return "success";
			} else {
				return "exist";
			}

		} else if (action.equals("reserve")) {
			long spaceid = Long.parseLong(request.getParameter("spaceid"));
			System.out.println("GuestActionController Post spaceid:" + spaceid);
			Space space = getSpaceDAO.getSpaceById(spaceid);

			String reserveday = request.getParameter("reserveday");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			int times = guest.getReservationTimes() + 1;
			System.out.println("GuestActionController Reserve action Date:" + reserveday + "/starttime:" + starttime
					+ "/endtime:" + endtime);
			Enterprise enterprise = space.getFloorname().getBuildingname().getEnterprisename();
			Reservation reservation = guestActionDAO.createReservation(space, guest, enterprise, reserveday, starttime,
					endtime);
			guestActionDAO.addReservationTimes(guest, times);

			return "success";
		} else if (action.equals("deletesubscription")) {
			int subscriptionid = Integer.parseInt(request.getParameter("subscriptionid"));
			System.out.println("GuestActionController Post subscriptionid:" + subscriptionid);
			guestActionDAO.deleteSubscription(subscriptionid);

			return "success";
		} else if (action.equals("cancel")) {
			String reason = request.getParameter("reason");
			String reservationidST = request.getParameter("reservationid");
			int reservationid = Integer.parseInt(reservationidST);
			System.out.println("CreditActionController POST reason:" + reason + " /reservationid:" + reservationid);
			int times = guest.getCancelTimes() + 1;
			guestActionDAO.cancelReservation(reservationid, guest, reason);
			guestActionDAO.addCancelTimes(guest, times);

			return "success";
		} else if (action.equals("update")) {
			String reservationidST = request.getParameter("reservationid");
			int reservationid = Integer.parseInt(reservationidST);
			System.out.println("CreditActionController POST /reservationid:" + reservationid);

			String reserveday = request.getParameter("reserveday");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			
			System.out.println("GuestActionController Reserve action Date:" + reserveday + "/starttime:" + starttime
					+ "/endtime:" + endtime);
			guestActionDAO.updateReservation(reservationid, reserveday, starttime, endtime);

			return "success";
		} else {
			return "error";

		}

	}

//	@RequestMapping(value = "GuestAction", method = RequestMethod.GET)
//	protected ModelAndView handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		Guest guest = (Guest) session.getAttribute("name");
//		String action = request.getParameter("action");
//		System.out.println("GuestActionController GET action:" + action);
//		ModelAndView mv = new ModelAndView();
//		GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
//		GuestActionDAO spaceActionDAO = new GuestActionDAO();
//		if (action.equals("CheckSpace")) {
//			mv.addObject("spacelist", setSpaceDAO.getSpaceList());
//			mv.setViewName("Guest-CheckSpace");
//		} else if (action.equals("Subscription")) {
//			mv.addObject("subscriptionlist", spaceActionDAO.getSubscription(guest));
//			mv.setViewName("Guest-Subscription");
//		} else if (action.equals("Reservation")) {
//			mv.addObject("reservationlist", spaceActionDAO.getReservation(guest));
//			mv.setViewName("Guest-Reservation");
//		} else if (action.equals("Home")) {
//			mv.addObject("subscriptionlist", spaceActionDAO.getSubscription(guest));
//			mv.addObject("reservationlist", spaceActionDAO.getReservation(guest));
//			mv.addObject("spacelist", setSpaceDAO.getSpaceList());
//			mv.setViewName("Guest-Home");
//		}
//		return mv;
//	}
	@RequestMapping(value = "CheckSpace", method = RequestMethod.GET)
	protected ModelAndView CheckSpace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("userRole").equals("guest")){
			Guest guest = (Guest) session.getAttribute("name");
			String action = request.getParameter("action");
			System.out.println("GuestActionController GET action:" + action);
			
			GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
			GuestActionDAO spaceActionDAO = new GuestActionDAO();
			mv.addObject("spacelist", setSpaceDAO.getSpaceList());
			mv.setViewName("Guest-CheckSpace");
			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}
	@RequestMapping(value = "Subscription", method = RequestMethod.GET)
	protected ModelAndView Subscription(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("userRole").equals("guest")){
			Guest guest = (Guest) session.getAttribute("name");
			String action = request.getParameter("action");
			System.out.println("GuestActionController GET action:" + action);
			GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
			GuestActionDAO spaceActionDAO = new GuestActionDAO();
			mv.addObject("subscriptionlist", spaceActionDAO.getSubscription(guest));
			mv.setViewName("Guest-Subscription");
			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}
	@RequestMapping(value = "Reservation", method = RequestMethod.GET)
	protected ModelAndView Reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("userRole").equals("guest")){
			
			Guest guest = (Guest) session.getAttribute("name");
			String action = request.getParameter("action");
			System.out.println("GuestActionController GET action:" + action);
			
			GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
			GuestActionDAO spaceActionDAO = new GuestActionDAO();
			mv.addObject("reservationlist", spaceActionDAO.getReservation(guest));
			mv.setViewName("Guest-Reservation");
			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}
	@RequestMapping(value = "Home", method = RequestMethod.GET)
	protected ModelAndView Home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("userRole").equals("guest")){
			Guest guest = (Guest) session.getAttribute("name");
			String action = request.getParameter("action");
			System.out.println("GuestActionController GET action:" + action);
			GetSpaceDAO setSpaceDAO = new GetSpaceDAO();
			GuestActionDAO spaceActionDAO = new GuestActionDAO();
			mv.addObject("subscriptionlist", spaceActionDAO.getSubscription(guest));
			mv.addObject("reservationlist", spaceActionDAO.getReservation(guest));
			mv.addObject("spacelist", setSpaceDAO.getSpaceList());
			mv.setViewName("Guest-Home");
			return mv;
		}else{
			mv.setViewName("error");
			return mv;
		}
	}
	@RequestMapping(value = "GuestRoleAccount", method = RequestMethod.GET)
	protected String CreditRoleAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "GuestRoleAccount";
	}

}
