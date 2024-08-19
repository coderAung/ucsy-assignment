package edu.ucsy.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.entity.Post;

public class PostModelImpl implements PostModel {
	
	private static final String URL = "jdbc:mysql://localhost:3306/blog_db";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	@Override
	public Post save(Post p) {
		var sql = "insert into posts (title, description, author, category) values (?, ?, ?, ?)";
		try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
				var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, p.getTitle());
				stmt.setString(2, p.getDescription());
				stmt.setString(3, p.getAuthor());
				stmt.setString(4, p.getCategory());
			
				stmt.executeUpdate();
				return p;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Post> getByAuthor(String author) {
		var sql = "select * from posts where author = ?";
		try (var conn = DriverManager.getConnection(URL, USER, PASSWORD);
				var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, author);
			var rs = stmt.executeQuery();
			var posts = new ArrayList<Post>();
			while(rs.next()) {
				var post = new Post(
						rs.getString("title"), 
						rs.getString("description"), 
						rs.getString("author"),
						rs.getString("category"));
				post.setId(rs.getInt("id"));
				post.setPostedDate(rs.getDate("posted_date").toLocalDate());
				
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
