package edu.ucsy.controller;

import java.io.IOException;

import edu.ucsy.entity.Post;
import edu.ucsy.model.PostModel;
import edu.ucsy.model.PostModelImpl;
import edu.ucsy.utils.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post")
public class PostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PostModel postModel;
	
	@Override
	public void init() throws ServletException {
		postModel = new PostModelImpl();
		getServletContext().setAttribute("formatter", DateTimeHelper.FORMATTER);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var author = req.getParameter("author");
		
		if(null == author || author.isBlank() || author.isEmpty()) {
			var path = getServletContext().getContextPath().concat("/");
			resp.sendRedirect(path);
		} else {
			var posts = postModel.getByAuthor(author);
			req.setAttribute("posts", posts);
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var title = req.getParameter("title");
		var description = req.getParameter("description");
		var author = req.getParameter("author");
		var category = req.getParameter("category");
		
		var post = new Post(title, description, author, category);
		post = postModel.save(post);
		
		var session = req.getSession(true);
		session.setAttribute("msg", "Successfully Saved!");
		
		var path = getServletContext().getContextPath().concat("/");
		resp.sendRedirect(path);
		
	}
}
