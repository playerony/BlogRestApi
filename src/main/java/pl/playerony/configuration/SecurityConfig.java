package pl.playerony.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pl.playerony.exception.SecurityException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private DataSource dataSource;
	
	@Autowired
	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws SecurityException {
		try {
			auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT u.login, u.password, u.roleId FROM USERS u, ROLES r WHERE r.id=u.roleId AND u.login=?")
				.authoritiesByUsernameQuery("SELECT u.login, r.name FROM USERS u, ROLES r where r.id=u.roleId and u.name=?");
		} catch (Exception e) {
			throw new SecurityException("Some problems by Security Configuration", e);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws SecurityException {
		try {
			http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/rest/**").permitAll()
				.antMatchers("/article/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
				.antMatchers("/comment/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
				.antMatchers("/role/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
				.antMatchers("/user/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
				.and().formLogin()
				.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/book/")
					.usernameParameter("login")
					.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.and().exceptionHandling().accessDeniedPage("/access-denied");
			
			http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			http.csrf().disable();
		} catch (Exception e) {
			throw new SecurityException("Some problems by Security Configuration", e);
		}
	}
	
}
