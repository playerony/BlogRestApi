package pl.playerony.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/blog");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("haslo");
		
		return driverManagerDataSource;
	}
	
}
