package com.shop.goodee.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	//아이디 찾기
	@GetMapping("find_id")
	public void getFindId()throws Exception {
		
	}
	@PostMapping("find_id")
	@ResponseBody
	public String getFindId(MemberVO memberVO)throws Exception {
		String id = memberService.getFindId(memberVO);
		
		return id;
	}
	//비번 찾기
	@GetMapping("find_pw")
	public void getFindPw()throws Exception {
		
	}
	@PostMapping("find_pw")
	@ResponseBody
	public String getFindPw(MemberVO memberVO)throws Exception{
		String email = memberService.getFindPw(memberVO);
		if(email != null) {
			mailService.sendMail(memberVO);			
		}
		return email;
	}

	/* 약관동의 */
	@GetMapping("agree")
	public void getAgree() {

	}
	// 아이디 중복 확인
	@GetMapping("idCheck")
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberService.getIdCheck(memberVO);
	}
	
	/* 회원가입 */
	@GetMapping("join")
	public void getJoin(@ModelAttribute MemberVO memberVO) {
		
	}
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, BindingResult bindingResult, ModelAndView mv, String yy, String mm, String dd, String e, String mailOption)throws Exception {
		
		//사용자 검증 메서드(id중복 체크, 이메일 입력 체크, 비번 일치 검증, 휴대번호 입력 검증)
		boolean check = memberService.getMemberError(memberVO, bindingResult);
//		check : true일 경우 검증 실패!
		if(check) {
			mv.setViewName("member/join");
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError:errors) {
				mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return mv;
		}
		
		int result = memberService.setJoin(memberVO, yy, mm, dd, e, mailOption);
		
		//회원가입(등급 생성 포함) 성공 시 회원가입 성공 페이지, 실패 시 회원가입 페이지
		if(result == 1) {
			mv.setViewName("redirect:../member/join_end");
			return mv;
		}else {
			mv.setViewName("redirect:../member/join");
			return mv;
		}
		
	}
	
	
	/* 회원가입 완료 */
	@GetMapping("join_end")
	public void getJoinEnd() {
		
	}
	
	//로그인(세션)
	@PostMapping("login")
	@ResponseBody
	public int getLogin(MemberVO memberVO,HttpSession session)throws Exception{
		memberVO = memberService.getLogin(memberVO);
		int result = 0;
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			result = 1;
		}
		
		return result;
	}
	
	//로그아웃(세션)
	@GetMapping("logout")
	public String setLogout(HttpSession session)throws Exception{
		session.invalidate();
		
		return "redirect:/";
	}
	
	/* 마이페이지 */
	@GetMapping("mypage")
	public ModelAndView getMypage(HttpSession session,MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/mypage");
		return mv;	
	}
	
	/* 마이페이지 - 프로필 수정 */
	@GetMapping("profile")
	public ModelAndView setProfile(HttpSession session,MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		if(memberVO == null) {
			mv.setViewName("redirect:/");
		}else {
			//id값으로 파일 조회 후 전송
			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setId(memberVO.getId());
			memberFileVO = memberService.getProfile(memberFileVO);
			mv.addObject("memberFileVO", memberFileVO);
			
			//회원 정보 담아서 전송
			memberVO = memberService.getMypage(memberVO);
			mv.addObject("memberVO", memberVO);
			mv.setViewName("/member/profile");			
		}
		
		return mv;
	}
	
	@PostMapping("profile")
	public ModelAndView setProfile(HttpSession session, MemberVO memberVO, @RequestParam("file")MultipartFile multipartFile, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		MemberFileVO memberFileVO = memberService.setProfile(memberVO, multipartFile);
		
		mv.setViewName("redirect:/member/profile");
		mv.addObject("memberFileVO", memberFileVO);

		return mv;
	}
	
	// 프로필사진 삭제버튼을 누르면 default이미지로 update함.
	@PostMapping("profile_delete")
	@ResponseBody
	public String setProfileDelete(MemberVO memberVO, ModelAndView mv)throws Exception {
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("user.webp");
		memberFileVO.setOriName("user.webp");
		memberFileVO.setId(memberVO.getId());
		
		int result = memberService.setProfileUpdate(memberFileVO);
		if(result == 1) {
			return "user.webp";
		}else {
			return "업데이트 실패";
		}

	}
	
	/* 내포인트 */
	@GetMapping("point")
	public ModelAndView getPoint(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/point");
		return mv;
	}
	
	/* 적립예정 포인트 조회 */
	@PostMapping("getPoint")
	@ResponseBody
	public int getPoint(HttpSession session, MemberVO memberVO)throws Exception{
		memberVO = (MemberVO) session.getAttribute("member");
		return memberService.getPoint(memberVO);
	}
	
	/* 3일 뒤 적립 된 회원 포인트 조회 */
	@PostMapping("getPoint_3")
	@ResponseBody
	public int getPoint_3(HttpSession session, MemberVO memberVO)throws Exception{
		memberVO = (MemberVO) session.getAttribute("member");
		
		return memberService.getPoint_3(memberVO);
	}
	
	/* 출금신청 후 적립 포인트 변경 */
	@PostMapping("setResultPoint_3")
	@ResponseBody
	public int setResultPoint_3(HttpSession session, MemberVO memberVO, Long point)throws Exception{
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO.setPoint_3(point);
		return memberService.setResultPoint_3(memberVO);
	}
	
	/* 출금신청 후 출금 예정 포인트 변경 */
	@PostMapping("setResultPoint")
	@ResponseBody
	public Long setResultPoint(HttpSession session, MemberVO memberVO, Long point)throws Exception{
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO.setPoint_result(point);
		memberService.setResultPoint(memberVO);
		Long result = memberVO.getPoint_result();
		return result;
	}

	
	/* 내등급 */
	@GetMapping("grade")
	public ModelAndView getGrade(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/grade");
		return mv;
	}
	
	/* 내 설정 - 본인확인 */
	@GetMapping("set")
	public ModelAndView getSet(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/set");
		return mv;
	}
	
	/* 내 설정 - 본인확인 */
	@PostMapping("pwCheck")
	@ResponseBody
	public int getPwCheck(HttpSession session, MemberVO memberVO)throws Exception{
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute("member");
		memberVO.setId(sessionMemberVO.getId());
		
		return memberService.getPwCheck(memberVO);
	}
	/* 내 설정 - 내 정보 변경*/
	@GetMapping("set_up")
	public ModelAndView getSetUp(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/set_up");
		
		return mv;
	}
	
	/* 내 설정 - 이메일 변경 */
	@PostMapping("changeEmail")
	@ResponseBody
	public int setChangeEmail(MemberVO memberVO, HttpSession session, String e, String mailOption)throws Exception {
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute("member");
		memberVO.setId(sessionMemberVO.getId());
		memberVO.setEmail(memberVO.getEmail());
		
		int result = memberService.setChangeEmail(memberVO, e, mailOption);
		
		return result;
	}
	
	/* 내 설정 - 전화번호 변경 */
	@PostMapping("changePhone")
	@ResponseBody
	public int setChangePhone(MemberVO memberVO, HttpSession session)throws Exception {
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute("member");
		memberVO.setId(sessionMemberVO.getId());
		memberVO.setPhone(memberVO.getPhone());
		
		int result = memberService.setChangePhone(memberVO);
		
		return result;
	}
	
	/* 내 설정 - 비밀번호 변경*/
	@GetMapping("set_pw")
	public ModelAndView getSetPw(MemberVO memberVO, HttpSession session, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/set_pw");
		
		return mv;
	}
	@PostMapping("changePw")
	@ResponseBody
	public int setChangePw(MemberVO memberVO, HttpSession session)throws Exception {
		MemberVO sessionMemberVO = (MemberVO) session.getAttribute("member");
		memberVO.setId(sessionMemberVO.getId());
		memberVO.setPw(memberVO.getPw());
		
		int result = memberService.setChangePw(memberVO);
		return result;
	}
	
	
	/* 내 설정 - 회원 탈퇴*/
	@GetMapping("withdrawal")
	public ModelAndView getWithdrawal(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/withdrawal");

		return mv;
	}
	
	/* 회원 탈퇴 */
	@PostMapping("withdrawal")
	@ResponseBody
	public int setWithdrawal(HttpSession session, MemberVO memberVO)throws Exception{
		memberVO = (MemberVO) session.getAttribute("member");
		
		return memberService.setWithdrawal(memberVO);
	}
	
	/* 내 상품 */
	@GetMapping("product")
	public ModelAndView getProduct(HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception {
		memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.addObject("memberVO", memberVO);
		mv.setViewName("/member/product");
		return mv;
	}
	

}