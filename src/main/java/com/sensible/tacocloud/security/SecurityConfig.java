package com.sensible.tacocloud.security;

import com.sensible.tacocloud.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * 基于内存的用户存储
     * @param auth
     * @throws Exception

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("superadmin")
                .password("admin")
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password("123456")
                .authorities("ROLES_USER");
    }*/



    /**
     * 基于Jdbc的用户存储
     * @param auth
     * @throws Exception

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new MyPasswordEncoder()).dataSource(dataSource);
    }*/

    /**
     * 基于LDAP的用户存储
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("uid={0}")
                .groupSearchBase("ou=groups").groupSearchFilter("member={0}");
    }
}
