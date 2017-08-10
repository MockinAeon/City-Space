package com.wxr.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wxr.spring.dao.ManageSpaceDAO;
import com.wxr.spring.dao.GetSpaceDAO;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Building;
import com.wxr.spring.pojo.City;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Floor;
import com.wxr.spring.pojo.Space;

@Controller
public class ManageSpaceController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ManageSpaceController.class);
	@RequestMapping(value = "managespace", method = RequestMethod.POST)
	public @ResponseBody String Login(HttpServletRequest request) throws AdException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ManageSpaceDAO manageSpaceDAO = new ManageSpaceDAO();
		GetSpaceDAO setSpaceDAO = new GetSpaceDAO();

		// add city function
		if (action.equals("addcity")) {
			String cityname = request.getParameter("cityname");
			System.out.println("action/" + action + "/cityname/" + cityname);

			// check whether exist, if null create new
			City city = manageSpaceDAO.checkCity(cityname);
			if (city == null) {
				manageSpaceDAO.createCity(cityname);
				System.out.println("citysuccess");
				session.setAttribute("citylist", setSpaceDAO.getCityList());
				return "success";
			} else {
				System.out.println("cityexist");
				return "exist";
			}
		}

		// ADD ENTERPRISE
		else if (action.equals("addenterprise")) {
			String enterprisename = request.getParameter("enterprisename");
			String cityname = request.getParameter("cityselect");
			City city = manageSpaceDAO.checkCity(cityname);
			System.out.println("action/" + action + "/enterprisename/" + enterprisename);
			System.out.println("cityselect/" + cityname);

			// check whether exist, if null create new
			Enterprise enterprise = manageSpaceDAO.checkEnterprise(enterprisename);
			if (enterprise == null) {
				manageSpaceDAO.createEnterprise(city, enterprisename);
				System.out.println("enterprisesuccess");
				session.setAttribute("enterpriselist", setSpaceDAO.getEnterpriseList());
				return "success";
			} else {
				System.out.println("enterpriseexist");
				return "exist";
			}
		}

		// ADD building
		else if (action.equals("addbuilding")) {
			String buildingname = request.getParameter("buildingname");
			String enterprisename = request.getParameter("enterpriseselect");
			Enterprise enterprise = manageSpaceDAO.checkEnterprise(enterprisename);
			System.out.println("action/" + action + "/buildingname/" + buildingname);
			System.out.println("enterpriseselect/" + enterprise.getEnterprisename());

			// check whether exist, if null create new
			Building building = manageSpaceDAO.checkBuilding(buildingname);
			if (building == null) {
				manageSpaceDAO.createBuilding(enterprise, buildingname);
				System.out.println("buildingsuccess");
				session.setAttribute("buildinglist", setSpaceDAO.getBuildingList());
				return "success";
			} else {
				System.out.println("buildingexist");
				return "exist";
			}
		}

		// ADD FLOOR
		else if (action.equals("addfloor")) {
			String floorname = request.getParameter("floorname");
			String buildingname = request.getParameter("buildingselect");
			Building building = manageSpaceDAO.checkBuilding(buildingname);
			System.out.println("action/" + action + "/floorname/" + floorname);
			System.out.println("buildingselect/" + buildingname);
			// check whether exist, if null create new
			Floor floor = manageSpaceDAO.checkFloor(floorname);
			if (floor == null) {
				manageSpaceDAO.createFloor(building, floorname);
				System.out.println("floorsuccess");
				session.setAttribute("floorlist", setSpaceDAO.getFloorList());
				return "success";
			} else {
				System.out.println("floorexist");
				return "exist";
			}
		}
		// ADD SPACE
		else if (action.equals("addspace")) {
			String spacename = request.getParameter("spacename");
			String floorname = request.getParameter("floorselect");
			String canReserveST = request.getParameter("canReserve");
			boolean canReserve;
			if (canReserveST.equals("1")) {
				canReserve = true;
			} else {
				canReserve = false;
			}
			Floor floor = manageSpaceDAO.checkFloor(floorname);
			System.out.println("action/" + action + "/spacename/" + spacename);
			System.out.println("buildingselect/" + floorname);
			// check whether exist, if null create new
			Space space = manageSpaceDAO.checkSpace(spacename);
			if (space == null) {
				manageSpaceDAO.createSpace(floor, spacename, canReserve);
				System.out.println("spacesuccess");
				session.setAttribute("spacelist", setSpaceDAO.getSpaceList());
				return "success";
			} else {
				System.out.println("spaceexist");
				return "exist";
			}
		}

		else {
			System.out.println("cityerror");
			return "error";
		}

	}

//	@RequestMapping(value = "", method = RequestMethod.GET)
//	protected ModelAndView handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String action = request.getParameter("action");
//		System.out.println("ManageSpaceController GET ,action/" + action);
//		ModelAndView mv = new ModelAndView();
//		if (action.equals("managecity")) {
//			mv.setViewName("Sysadmin");
//		} else if (action.equals("manageenterprise")) {
//			mv.setViewName("Sysadmin-enterprise");
//		} else if (action.equals("managebuilding")) {
//			mv.setViewName("Sysadmin-building");
//		} else if (action.equals("managefloor")) {
//			mv.setViewName("Sysadmin-floor");
//		} else if (action.equals("managespace")) {
//			mv.setViewName("Sysadmin-space");
//		}
//
//		return mv;
//	}

	@RequestMapping(value = "managecity", method = RequestMethod.GET)
	protected String managecity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin";
		} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "manageenterprise", method = RequestMethod.GET)
	protected String manageenterprise(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-enterprise";
		} else {
			return "error";
		}
	}
	@RequestMapping(value = "managebuilding", method = RequestMethod.GET)
	protected String managebuilding(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-building";
			} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "managefloor", method = RequestMethod.GET)
	protected String managefloor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-floor";
			} else {
			return "error";
		}
		
	}
	@RequestMapping(value = "managespace", method = RequestMethod.GET)
	protected String managespace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole").equals("sysadmin")) {
			return "Sysadmin-space";
			} else {
			return "error";
		}
		
	}
	

}
