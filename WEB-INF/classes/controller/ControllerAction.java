package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ControllerAction extends HttpServlet {
	
	  //명령어와 명령어 처리클래스를 쌍으로 저장
	  //키값,저장할값(객체)
	  private Map commandMap = new HashMap();

	  //매개변수에 대한 초기화작업
	  public void init(ServletConfig config)
		    throws ServletException{
		 //web.xml에서 파일을 불러온다.
		String props =
			config.getInitParameter("propertyConfig");
	   
	    //분리하기위한 명령어와 연결객체를 저장
		Properties pr = new Properties();

		FileInputStream f = null;
		try
		{
		  f = new FileInputStream(props);
		  pr.load(f);//CommandPro.properties파일
		}
		catch (IOException e)
		{
		  throw new ServletException(e);
		}finally{
	      if(f!=null) 
			try{f.close();}catch(IOException ex){}
		}//finally

	    //메모리상에 저장된 키값을 불러와서 객체를생성
	    //                  Set의객체명.iterator();
		Iterator keyiter = pr.keySet().iterator();

		 while(keyiter.hasNext()){
	       String command = (String)keyiter.next();
		  String className = pr.getProperty(command);
		 
		   try
		   {
			//DB드라이버를 로드시킬때 사용한 메서드
			//해당문자열을 클래스로 로드
			Class commandClass = 
				        Class.forName(className);

			//해당클래스의 객체를 생성
			Object commandInstance = 
				         commandClass.newInstance();

			//Map객체인 commandMap에 객체를 저장
			//요청한 명령어에 따른 객체를 얻어오기위해
			//메모리상에 저장
	         commandMap.put(command,commandInstance);

		   }catch (ClassNotFoundException e)
		   {
			  throw new ServletException(e);
		   }catch (InstantiationException e)
		   {
			  throw new ServletException(e);
		   }catch (IllegalAccessException e)
		   {
			  throw new ServletException(e);
		   }
		 }//while
	  }//init()

	  //요청->Get->doGet
	  public void doGet(HttpServletRequest request,
		              HttpServletResponse response)
	          throws ServletException,IOException{
	       requestPro(request,response);
	  }

	  //요청->Post->doPost
	  public void doPost(HttpServletRequest request,
		              HttpServletResponse response)
	          throws ServletException,IOException{
	        requestPro(request,response);
	  }
	 
	  //Get,Post든지 요청이 들어오면 전부처리
	  private void requestPro(HttpServletRequest request,
              			HttpServletResponse response) 
            		  throws ServletException, IOException {

		String view=null;//요청명령어에 따라서 이동할 페이지 저장
	    // /list.do=action.ListAction->/list.jsp
	    // /writeForm.do=action.WriteFormAction
	    	
	    //ListAction com=null;
	    //WriteFormAction com=null;
	    //,,,
	    CommandAction com=null;//어떠한 자식클래스의 객체라도 부모형으로 형변환
	    //CommandAction com=new ListAction();
	    //ListAction com=new ListAction();
	    //WriteFormAction com=new WriteFormAction();

	    try
		   {
			//요청정보->/board2/list.do
			//          /board2
			String command = request.getRequestURI();
			System.out.println("첫번command="+command);
			//->/board2
			System.out.println("request.getContextPath()="
			                +request.getContextPath());
			if(command.indexOf(request.getContextPath())==0){
		      command = command.substring
				   (request.getContextPath().length());
			 }//if
			  System.out.println("두번째command="+command);
			  com = (CommandAction)commandMap.get(command);//=>/list.do
		      System.out.println("com="+com);//action.ListAction@주소값
		      view = com.requestPro(request,response);
			  System.out.println("view="+view);//list.jsp
			 
		   }
		   catch (Throwable e)
		   {
			 throw new ServletException(e);
		   }
		   //실제 페이지이동하는 구문
		    RequestDispatcher dispatcher =
				    request.getRequestDispatcher(view);
			dispatcher.forward(request,response);
		  }//requestPro
		}//class