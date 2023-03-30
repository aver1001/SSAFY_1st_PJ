package web.model.service;

import web.model.dao.MemberDAO;
import web.model.vo.MemberVO;
import web.util.MyException;

public class MemberService {
	MemberDAO memberDAO;
	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public void memberInsert(MemberVO memberVO) throws MyException {
		memberDAO.memberInsert(memberVO);
	}

	public MemberVO login(MemberVO memberVO) throws MyException {
		return memberDAO.login(memberVO);
		
	}

	public void updateInfo(MemberVO memberVO) throws MyException {
		memberDAO.updateInfo(memberVO);
	}

	public void deleteUser(MemberVO memberVO) throws MyException {
		memberDAO.deleteUser(memberVO);
	}

	public boolean idCheck(String id) throws MyException {
		return memberDAO.isCheck(id);
	}
}
