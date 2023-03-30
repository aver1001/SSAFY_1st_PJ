package web.model.service;

import java.util.ArrayList;


import web.model.dao.*;
import web.model.vo.Location;
import web.model.vo.locVO;
import web.util.MyException;

public class MapService {
	MapDAO mapDao;
	
	public MapService() {
		mapDao = new MapDAO();
	}
	public ArrayList<Location> searchLoc(locVO locVo) throws MyException {
		return mapDao.searchLoc(locVo);
	}
	public ArrayList<Sido> searchOption() throws MyException {
		return mapDao.searchOption();
	}

}
