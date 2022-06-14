package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDAO;
import login.LoginDTO;
import member.MemberDAO;
import member.MemberDTO;

// /writePro.do
public class AdMemberUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//한글처리
		request.setCharacterEncoding("utf-8");
		String m_id=request.getParameter("m_id");
		String m_status=request.getParameter("m_status");
		
		String pageNum=request.getParameter("pageNum");
		System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);//0
		//--------------------------------------------------------
		request.setCharacterEncoding("utf-8");
		MemberDTO article=new MemberDTO();	     
		article.setM_id(request.getParameter("m_id"));
		article.setM_status(request.getParameter("m_status")); // Member status update
		System.out.println(m_id+", "+m_status);
		
		LoginDAO ldao=new LoginDAO();
		LoginDTO ldto=new LoginDTO();
		ldto.setM_id(request.getParameter("m_id"));
		ldto.setM_status(request.getParameter("m_status")); // Member status update

		     
		MemberDAO dbPro=new MemberDAO();
		int check=dbPro.MemUpdateArticle(article);
		int check2=dbPro.LogUpdateArticle(ldto);
		System.out.println("Check >>>>"+check);
		System.out.println("Check >>>>"+check2);
		   
		//2개의 공유값이 필요
		request.setAttribute("pageNum",pageNum);//수정할 페이지로 이동
		request.setAttribute("check",check);//데이터 수정성공유무
		request.setAttribute("check2",check2);//데이터 수정성공유무
		    
		return "/Admin/MemberUpdateProc.jsp";
	}
}
