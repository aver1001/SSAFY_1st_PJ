package web.model.vo;

public class locVO {
	private String areaCode,contentTypeId,keyword;

	public locVO() {}

	public locVO(String areaCode, String contentTypeId, String keyword) {
		setAreaCode(areaCode);
		setContentTypeId(contentTypeId);
		setKeyword(keyword);
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		if (areaCode != null) {
			this.areaCode = areaCode;
		}
	}

	public String getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(String contentTypeId) {
		if (contentTypeId != null) {
			this.contentTypeId = contentTypeId;
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		if (keyword != null) {
			this.keyword = keyword;
		}
	}

	@Override
	public String toString() {
		return "locVO [areaCode=" + areaCode + ", contentTypeId=" + contentTypeId + ", keyword=" + keyword + "]";
	}
	
	
	
	
}
