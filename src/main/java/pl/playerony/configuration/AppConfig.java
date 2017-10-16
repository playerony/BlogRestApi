package pl.playerony.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.playerony.repository.ArticleRepository;
import pl.playerony.repository.CommentRepository;
import pl.playerony.repository.RoleRepository;
import pl.playerony.repository.UserRepository;
import pl.playerony.repository.impl.ArticleRepositoryImpl;
import pl.playerony.repository.impl.CommentRepositoryImpl;
import pl.playerony.repository.impl.RoleRepositoryImpl;
import pl.playerony.repository.impl.UserRepositoryImpl;

@Configuration
public class AppConfig {
	@Bean
	public RoleRepository roleRepository()
	{
		return new RoleRepositoryImpl();
	}
	
	@Bean
	public UserRepository userRepository()
	{
		return new UserRepositoryImpl();
	}
	
	@Bean
	public ArticleRepository articleRepository() 
	{
		return new ArticleRepositoryImpl();
	}
	
	@Bean
	public CommentRepository commentRepository()
	{
		return new CommentRepositoryImpl();
	}
}
