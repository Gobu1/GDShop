package com.shop.goodee.member;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.shop.goodee.util.FileManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.profile}")
	private String path;
	
	//아이디 찾기
	public String getFindId(MemberVO memberVO)throws Exception{
		String email = memberMapper.getFindId(memberVO);
		return email;
	}
	//비밀번호 찾기
	public String getFindPw(MemberVO memberVO)throws Exception{
		return memberMapper.getFindPw(memberVO);
	}
	
	//중복 아이디 조회
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberMapper.getIdCheck(memberVO);
	}
	
	//회원가입(입력)
	public int setJoin(MemberVO memberVO, String yy, String mm, String dd, String e, String mailOption)throws Exception {
		if(dd.length() == 1) {
			dd = '0'+dd;
		}
		memberVO.setBirth(yy+'-'+mm+'-'+dd);
		memberVO.setEmail(e+"@"+mailOption);
		
		//회원가입 시 default 이미지 넣기
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("user.webp");
		memberFileVO.setOriName("user.webp");
		memberFileVO.setId(memberVO.getId());
		
		int result = memberMapper.setJoin(memberVO);
		int success= 0;
		//회원가입 성공 시, result = 1 (회원등급 추가하기)
		if(result == 1) {
			success = memberMapper.setMemberRole(memberVO);
			memberMapper.setProfile(memberFileVO);
		}else {
			log.info("가입 실패!");
		}
		
		return success;
	}
	
	//사용자 검증 메서드(id중복 체크, 이메일 입력 체크, 비번 일치 검증, 휴대번호 입력 검증)
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		//check=false : 검증 성공(에러X)		
		boolean check=false;
		
		//1.annotation검증
		check = bindingResult.hasErrors();
		
		//2.id 중복 체크!
		int result = memberMapper.getIdCheck(memberVO);
		if(result > 0) {
			check = true;
			bindingResult.rejectValue("id","member.id.equal");
		}
		
		//3.비번 일치 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			check= true;
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		//4. 이메일 입력 검증
		if(memberVO.getMailOption().equals("선택")) {
			check=true;
			bindingResult.rejectValue("mailOption", "member.email.req");
		}
		
		//5. 휴대번호 입력 검증
		log.info("Phone : {}", memberVO.getPhone().length());
		if(memberVO.getPhone().length() > 11 || memberVO.getPhone().length() < 11) {
			check=true;
			bindingResult.rejectValue("phone", "member.phone.req");
		}
		
		return check;
	}
	
	//로그인
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberMapper.getLogin(memberVO);
	}
	
	// 마이페이지
	public MemberVO getMypage(MemberVO memberVO)throws Exception{
		return memberMapper.getMypage(memberVO);
	}
	
	//마이페이지 - 프로필 사진 추가
	public MemberFileVO setProfile(MemberVO memberVO, MultipartFile multipartFile)throws Exception{
		//회원 가입할 때 아이디정보 담아서 default이미지 저장하기
		//프로필 수정페이지에선 프로필페이지의 id을 조회해서 출력하기
		//저장할 경로 담은 file객체 생성
		File file = new File(path);
		log.info("file경로 확인 : {}",file);
		
		//파일이 없으면 생성
		if(!file.exists()) {
			file.mkdirs();
		}
		MemberFileVO memberFileVO = new MemberFileVO();
		String fileName = fileManager.saveFile(multipartFile, path);
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(multipartFile.getOriginalFilename());
		memberFileVO.setId(memberVO.getId());
		
		//MemberFileVO에 같은 아이디의 파일이 있다면 삭제하고 없으면 생성
		//아이디 조회, null일경우 생성
		int success = 0;
		MemberFileVO profileID = memberMapper.getProfile(memberFileVO);
		if(profileID == null) {
			int result = memberMapper.setProfile(memberFileVO);
			success = 1;
		}else {
			memberMapper.setProfileUpdate(memberFileVO);
		}
		
		return memberFileVO;
	}
	
	public MemberFileVO getProfile(MemberFileVO memberFileVO)throws Exception{
		return memberMapper.getProfile(memberFileVO);
	}
	
	public int setProfileUpdate(MemberFileVO memberFileVO)throws Exception{
		return memberMapper.setProfileUpdate(memberFileVO);
	}
	
	/* 내 설정 */
	//비밀번호 일치 확인(본인확인)
	public int getPwCheck(MemberVO memberVO)throws Exception{
		return memberMapper.getPwCheck(memberVO);
	}
	
	/* 메일주소 변경 */
	public int setChangeEmail(MemberVO memberVO, String e, String mailOption)throws Exception{
		memberVO.setEmail(e+"@"+mailOption);
		return memberMapper.setChangeEmail(memberVO);
	}
	
	/* 폰번호 변경 */
	public int setChangePhone(MemberVO memberVO)throws Exception{
		return memberMapper.setChangePhone(memberVO);
	}
	
	/* 비밀번호 변경 */
	public int setChangePw(MemberVO memberVO)throws Exception{
		return memberMapper.setChangePw(memberVO);
	}
	
	/* 회원 탈퇴 */
	public int setWithdrawal(MemberVO memberVO)throws Exception{
		return memberMapper.setWithdrawal(memberVO);
	}
	
	/* 회원 포인트 조회 */
	public int getPoint(MemberVO memberVO)throws Exception{
		return memberMapper.getPoint(memberVO);
	}
	
	/* 3일 뒤 적립 된 회원 포인트 조회 */
	public int getPoint_3(MemberVO memberVO)throws Exception{
		return memberMapper.getPoint_3(memberVO);
	}
	
	/* 출금신청 후 적립 포인트 변경 */
	public int setResultPoint_3(MemberVO memberVO)throws Exception{
		return memberMapper.setResultPoint_3(memberVO);
	}
	
	/* 출금신청 후 출금 예정 포인트 변경 */
	public int setResultPoint(MemberVO memberVO)throws Exception{
		return memberMapper.setResultPoint(memberVO);
	}

}