package edu.ucsy.model;

import java.util.List;

import edu.ucsy.entity.Post;

public interface PostModel {

	Post save(Post p);
	
	List<Post> getByAuthor(String author);
}
