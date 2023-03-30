package web.model.vo;

import java.io.Serializable;

public class Location implements Serializable{
	private String firstimage,title,addr1,addr2,mapy,mapx;

	public Location() {}

	public Location(String firstimage, String title, String addr1,String addr2, String mapy, String mapx) {
		setFirstimage(firstimage);
		setTitle(title);
		setAddr1(addr1);
		setAddr2(addr2);
		setMapy(mapy);
		setMapx(mapx);
	}

	public String getImg() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		if (firstimage != null) {
			this.firstimage = firstimage;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		}
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		if (addr1 != null) {
			this.addr1 = addr1;
		}
	}
	
	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		if (addr2 != null) {
			this.addr2 = addr2;
		}
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		if ( mapy != null) {
			this.mapy = mapy;
		}
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		if (mapx != null) {
			this.mapx = mapx;
		}
	}

	@Override
	public String toString() {
		return "Location [firstimage=" + firstimage + ", title=" + title + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", mapy=" + mapy + ", mapx=" + mapx + "]";
	}

}
