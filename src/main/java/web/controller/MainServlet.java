package web.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import web.listener.SessionConfig;
import web.model.dao.Sido;
import web.model.service.MapService;
import web.model.service.MemberService;
import web.model.vo.Location;
import web.model.vo.MemberVO;
import web.model.vo.locVO;
import web.util.MyException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	MemberService memberService;
	MapService mapService;
	ArrayList<Location> locations;
	ArrayList<Sido> sidoInfo;
	Gson gson;

	@Override
	public void init(ServletConfig config) throws ServletException {
		gson = new Gson();
		memberService = new MemberService();
		mapService = new MapService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
		String sign = json.get("sign").getAsString();

		JsonObject retJson = new JsonObject();
		try {
			if (sign != null) {
				switch (sign) {
				case "memberInsert":
					String name = json.get("name").getAsString();
					String id = json.get("id").getAsString();
					String pw = json.get("pw").getAsString();
					String email = json.get("email").getAsString();
					String loc = json.get("loc").getAsString();
					MemberVO memberVO = new MemberVO(name, id, pw, email, loc);

					memberService.memberInsert(memberVO);
					retJson.addProperty("ok", "회원가입 완료");
					break;
				case "idCheck":
					id = json.get("id").getAsString();

					boolean usable = memberService.idCheck(id);
					if (usable) {
						retJson.addProperty("ok", "사용가능");
					} else {
						retJson.addProperty("error", "사용불가능한 아이디입니다.");
					}
					break;
				case "login":
					id = json.get("id").getAsString();
					pw = json.get("pw").getAsString();
					memberVO = new MemberVO(id, pw);
					memberVO = memberService.login(memberVO);
					if (memberVO != null) {
						HttpSession session = request.getSession();
						session.setAttribute("memberVO", memberVO);

						String userId = SessionConfig.getSessionidCheck("login_id", id);
						System.out.println(id + " : " + userId);
						session.setAttribute("login_id", id);

						retJson.addProperty("ok", "로그인 완료");
					} else {
						retJson.addProperty("error", "다시 로그인해주세요.");
					}
					break;
				case "logout":
					HttpSession session = request.getSession(false);
					session.invalidate();
					break;
				case "info": // info 에 들어왔을 때 사용자의 정보를 불러오기
					session = request.getSession(false);
					if (session != null) {
						memberVO = (MemberVO) session.getAttribute("memberVO");
						if (memberVO != null) {

							String jsonMember = gson.toJson(memberVO);
							retJson.addProperty("userInfo", jsonMember);
						} else {
							retJson.addProperty("error", "로그인이 만료되었습니다. 다시 로그인해주세요.");
						}
					} else {
						retJson.addProperty("error", "잘못된 접근입니다.");
					}

					break;
				case "updateInfo": // 사용자 정보 수정하기
					session = request.getSession(false);

					name = json.get("name").getAsString();
					id = json.get("id").getAsString();
					pw = json.get("pw").getAsString();
					pw = json.get("pw").getAsString();
					email = json.get("email").getAsString();
					loc = json.get("loc").getAsString();
					memberVO = new MemberVO(name, id, pw, email, loc);

					memberService.updateInfo(memberVO);
					memberVO = new MemberVO(name, id, pw, email, loc);
					session.setAttribute("memberVO", memberVO);
					retJson.addProperty("ok", "수정 완료");
					break;
				case "deleteUser": // 사용자 탈퇴하기
					session = request.getSession(false);
					memberVO = (MemberVO) session.getAttribute("memberVO");

					memberService.deleteUser(memberVO);
					retJson.addProperty("ok", "탈퇴 완료");
					session.invalidate();
					break;

				// 지도 표시
				case "searchLoc":
					String areaCode = json.get("areaCode").getAsString();
					String contentTypeId = json.get("contentTypeId").getAsString();
					String keyword = json.get("keyword").getAsString();
					locVO locVo = new locVO(areaCode, contentTypeId, keyword);
					locations = mapService.searchLoc(locVo);
					retJson.addProperty("locations", gson.toJson(locations));
					break;
				case "searchOption":
					sidoInfo = mapService.searchOption();
					retJson.addProperty("sidoInfo", gson.toJson(sidoInfo));
					break;
				}
			} else {
				retJson.addProperty("error", "잘못된 접근입니다.");
			}
		} catch (MyException e) {
			retJson.addProperty("error", e.getMessage());
		}

		out.append(retJson.toString());
		out.close();
	}

}
