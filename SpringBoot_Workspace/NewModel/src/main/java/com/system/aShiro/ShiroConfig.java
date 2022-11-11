package com.system.aShiro;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

public class ShiroConfig {
	
	@Bean(name = "shiroFilterFactoryBean")
	public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
	    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
	    // 設定管理器
	    factoryBean.setSecurityManager(manager);
	    LinkedHashMap<String, String> map = new LinkedHashMap<>();
	    map.put("/Login", "anon");
	    map.put("/toLogin", "anon");
	    map.put("/assets/**", "anon");
	    map.put("/**", "authc");
	    factoryBean.setFilterChainDefinitionMap(map);
	    // 设置登录页面
	    factoryBean.setLoginUrl("/Login");
//		    factoryBean.setSuccessUrl("/login");
	    // 未授权页面
	    factoryBean.setUnauthorizedUrl("/unauth");
	    return factoryBean;
	}

	@Bean
	public DefaultWebSecurityManager manager(@Qualifier("myRealm") MyRealm myRealm){
	    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
	    manager.setRealm(myRealm);
	    return manager;
	}

	@Bean
	public MyRealm myRealm(){
	    return new MyRealm();
	}

	@Bean
	public ShiroDialect shiroDialect(){
	    return new ShiroDialect();
	}
}
