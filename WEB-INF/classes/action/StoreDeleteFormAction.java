package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreDeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int item_num=Integer.parseInt(request.getParameter("item_num"));//�Խù���ȣ
		String pageNum=request.getParameter("pageNum");//������ ��ȣ
	    System.out.println("deleteForm.do�� item_num=>"+item_num+",pageNum=>"+pageNum);
	    
	    request.setAttribute("item_num", item_num);
	    request.setAttribute("pageNum", pageNum);
		
		return "/3.Store/deleteForm.jsp"; 
	}

}
