package com.wxr.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Building;
import com.wxr.spring.pojo.City;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Floor;
import com.wxr.spring.pojo.Space;

public class GetSpaceDAO extends DAO {

	public GetSpaceDAO() {
	}

	public List getCityList() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(City.class);
			List<City> cityList = crit.list();
			commit();
			return cityList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

	public List getEnterpriseList() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Enterprise.class);
			List<Enterprise> enterpriseList = crit.list();
			commit();
			return enterpriseList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

	public List getBuildingList() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Building.class);
			List<Building> buildingList = crit.list();
			commit();
			return buildingList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

	public List getFloorList() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Floor.class);
			List<Floor> floorList = crit.list();
			commit();
			return floorList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

	public List getSpaceList() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			Criteria crit = getSession().createCriteria(Space.class);
			List<Space> spaceList = crit.list();
			commit();
			return spaceList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

	public Space getSpaceById(long spaceid) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			Criteria crit = getSession().createCriteria(Space.class);
			crit.add(Restrictions.eq("id",spaceid));
			Space space = (Space) crit.uniqueResult();
			commit();
			return space;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}

}