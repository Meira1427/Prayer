package controllers;

import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.CurrentDAO;
import data.PrayerReqDAO;
import entities.PrayerRequest;

@RestController
public class PrayerReqController {
	
	@Autowired
	PrayerReqDAO prayDao;
	
	@Autowired
	CurrentDAO currentDao;
	
	@RequestMapping(path="prayerping", method=RequestMethod.GET)
	public String ping() {
			return "prayerpong";
	}
	
	@RequestMapping(path="prayers", method=RequestMethod.GET) 
	public Collection<PrayerRequest> indexAll() {
			return prayDao.indexAll();
	}
	
	@RequestMapping(path="current", method=RequestMethod.GET) 
	public Collection<PrayerRequest> indexCurrentOnly() {
		return prayDao.indexCurrent();
	}
	
	@RequestMapping(path="prayers/{id}", method=RequestMethod.GET) 
	public PrayerRequest showPrayer(@PathVariable int id) {
		return prayDao.show(id);
	}
	
	@RequestMapping(path="prayers", method=RequestMethod.POST) 
	public PrayerRequest create(HttpServletRequest req, HttpServletResponse res, @RequestBody String prayerJson) {
		String ipAddress;
		String xForwardedForHeader = req.getHeader("X-Forwarded-For");
	    if (xForwardedForHeader == null) {
	        ipAddress = req.getRemoteAddr();
	    } else {
	        ipAddress = new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
	    }
		PrayerRequest pr = prayDao.create(prayerJson, ipAddress);
		if(pr == null) {
			res.setStatus(400);
			return null;
		}
		currentDao.updateCurrentList(pr);
		res.setStatus(201);
		return pr;
	}
	
	@RequestMapping(path="prayers/{id}", method=RequestMethod.PUT)
	public PrayerRequest update(@PathVariable int id, @RequestBody String prayJson) {
		return prayDao.update(id, prayJson);
	}
	
	@RequestMapping(path="prayers/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable int id) {
		return prayDao.delete(id);
	}
	

}
