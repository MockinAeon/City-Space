package com.wxr.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Building;
import com.wxr.spring.pojo.City;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Floor;
import com.wxr.spring.pojo.Space;

public class ManageSpaceDAO extends DAO {

	public ManageSpaceDAO() {
	}

	// city
	public City createCity(String cityname) throws AdException {
		try {
			cityname= clean(cityname);
			begin();
			System.out.println("inside DAO");

			City city = new City();
			city.setCityname(cityname);
			getSession().save(city);

			commit();
			return city;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating city: " + e.getMessage());
		}finally {
			close();
		}

	}

	public City checkCity(String cityname) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from City where cityname = :cityname");
			q.setString("cityname", cityname);
			City city = (City) q.uniqueResult();
			commit();
			return city;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get city " + cityname, e);
		}finally {
			close();
		}
	}

	// enterprise

	public Enterprise createEnterprise(City city, String enterprisename) throws AdException {
		try {
			enterprisename=clean(enterprisename);
			begin();
			System.out.println("inside DAO");
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename(enterprisename);
			enterprise.setCityname(city);
			getSession().save(enterprise);
			commit();
			return enterprise;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating enterprise: " + e.getMessage());
		}finally {
			close();
		}

	}

	public Enterprise checkEnterprise(String enterprisename) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Enterprise where enterprisename = :enterprisename");
			q.setString("enterprisename", enterprisename);
			Enterprise enterprise = (Enterprise) q.uniqueResult();
			commit();
			return enterprise;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get enterprise " + enterprisename, e);
		}finally {
			close();
		}
	}

	// enterprise

	public Building createBuilding(Enterprise enterprisename, String buildingname) throws AdException {
		try {
			buildingname=clean(buildingname);
			begin();
			System.out.println("inside DAO");
			Building building = new Building();
			building.setBuildingname(buildingname);
			building.setEnterprisename(enterprisename);
			getSession().save(building);
			commit();
			return building;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating building: " + e.getMessage());
		}finally {
			close();
		}

	}

	public Building checkBuilding(String buildingname) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Building where buildingname = :buildingname");
			q.setString("buildingname", buildingname);
			Building building = (Building) q.uniqueResult();
			commit();
			return building;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get building " + buildingname, e);
		}finally {
			close();
		}
	}

	// floor

	public Floor createFloor(Building buildingname, String floorname) throws AdException {
		try {
			floorname=clean(floorname);
			begin();
			System.out.println("inside DAO");
			Floor floor = new Floor();
			floor.setFloorname(floorname);
			floor.setBuildingname(buildingname);
			getSession().save(floor);
			commit();
			return floor;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating floor: " + e.getMessage());
		}finally {
			close();
		}

	}

	public Floor checkFloor(String floorname) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Floor where floorname = :floorname");
			q.setString("floorname", floorname);
			Floor floor = (Floor) q.uniqueResult();
			commit();
			return floor;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get floor " + floorname, e);
		}finally {
			close();
		}
	}
	// space

	public Space createSpace(Floor floorname, String spacename,boolean canReserve) throws AdException {
		try {
			spacename = clean(spacename);
			begin();
			System.out.println("inside DAO");
			Space space = new Space();
			space.setSpacename(spacename);
			space.setFloorname(floorname);
			space.setSpaceStatus("Empty");
			space.setCanReserve(canReserve);
			getSession().save(space);
			commit();
			return space;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating space: " + e.getMessage());
		}finally {
			close();
		}

	}

	public Space checkSpace(String spacename) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Space where spacename = :spacename");
			q.setString("spacename", spacename);
			Space space = (Space) q.uniqueResult();
			commit();
			return space;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + spacename, e);
		}finally {
			close();
		}
	}
	private String clean(String value) {
        value = value.replaceAll("<", "").replaceAll(">", "");
        value = value.replaceAll("\\(", "").replaceAll("\\)", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "");
        value = value.replaceAll("script", "");
        return value;
    }
}