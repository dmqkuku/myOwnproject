package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Card;
import model.Dealer;
import model.Deck;
import model.User;
@WebServlet(
		name = "Control",
		urlPatterns = "/Control"
		)
public class Control extends HttpServlet {

	private Connection conn;
	private CallableStatement stmt;
	private DataSource dataFactory;
	private PreparedStatement pstmt;

	protected void doHit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	protected void doStay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	protected void doSplit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	
	protected void initiateBlackJack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Card> userHand = new HashMap<String, Card>();
		Map<String, Card> dealerHand = new HashMap<String, Card>();
		Deck deck = new Deck();
		User mainUser = new User();
		Dealer dealer = new Dealer();
		userHand.put(mainUser.getIndex(), deck.numGen());
		userHand.put(mainUser.getIndex(), deck.numGen());
		
		dealerHand.put(dealer.getIndex(), deck.numGen());
		dealerHand.put(dealer.getIndex(), deck.numGen());
		
		req.setAttribute("userHand", userHand);
		req.setAttribute("dealerHand", dealerHand);
		
		String url = "/BlackJack.jsp";
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		String url = "/Home.jsp";
		if(action == null) {
		
		}else {
			if(action.equals("goToReg")) {
				url = "/Register.jsp";
				getServletContext().getRequestDispatcher(url).forward(req, resp);
			}
			}if(action.equals("doBlackJack"))
				initiateBlackJack(req, resp);
		}
		
	
	
	protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			conn = dataFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "{? = call find_user(?, ?) }";		//다른 메소드로 분리시켜 깔끔하게 만들 수 있을것이다.
		String url = "/Home.jsp";
		
		
		try{
			conn.setAutoCommit(true);
			stmt = conn.prepareCall(query);
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			System.out.println(req.getParameter("id"));
			stmt.setString(2, req.getParameter("id"));
			stmt.setString(3, req.getParameter("password"));
			boolean res = stmt.execute();
			System.out.println(res);
			int result = stmt.getInt(1);
			System.out.println(result);
			if(result ==  1) {
				url = "/BlackJack.jsp";
			}else if(result == 0) {
				url = "/Register.jsp";
			}else
				url = "/Home.jsp"; //error로 이동하도록
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
	
	protected void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		try {
			conn = dataFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "/Home.jsp";
		
		String query = "insert into user_table values(? , ?)";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, req.getParameter("id"));
			pstmt.setString(2, req.getParameter("password"));
			int result = pstmt.executeUpdate();
			System.out.println(result);
			url = "/Home.jsp";
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt.close();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = req.getParameter("action");
		String url = "/Home.jsp";
		if(action == null) {
			url = "/Home.jsp";
			getServletContext().getRequestDispatcher(url).forward(req, resp);
		}else {
			if(action.equals("login")) {
				doLogin(req, resp);
			}
			 else if(action.equals("register")) {
				doRegister(req, resp);
			}
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		super.init();
	}

	
}
