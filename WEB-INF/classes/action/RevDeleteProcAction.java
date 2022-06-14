package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.RevBoardDAO;

//UpdateProAction을 save as DeleteProAction
public class RevDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	     String pageNum=request.getParameter("pageNum");//직접 입력 X
	     int num=Integer.parseInt(request.getParameter("com_num"));//직접 입력 X
	     System.out.println("RevDeleteProc.do에서의 pageNum=>"+pageNum
	    		 +"num=>"+num);
	     //---------------------------------------------------------------------------
	     RevBoardDAO dbPro=new RevBoardDAO();
	     int check=dbPro.RevDeleteArticle(num);
	     //2개의 공유값이 필요
	     request.setAttribute("pageNum", pageNum);//삭제한 레코드가 있는 페이지로 이동
	     request.setAttribute("check", check);//${check} 데이터 삭제성공유무
	     
		return "/8.RevBoard/RevDeleteProc.jsp";//updatePro.jsp와 소스코드가 동일하다.
	}

}
