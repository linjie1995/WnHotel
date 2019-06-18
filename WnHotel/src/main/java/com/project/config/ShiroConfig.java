package com.project.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.shiro.MyRealm;

@Configuration
public class ShiroConfig {
	/**
	 *	设置地址拦截器
	 * @param securityManager
	 * @return
	 */
	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager) {
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		//需要注入安全管理器
		bean.setSecurityManager(securityManager);
		//认证失败需要跳转的地址
		bean.setLoginUrl("/login.html");
		//授权失败需要跳转的地址
		bean.setUnauthorizedUrl("/fail.html");
		
		//设置访问路径的权限
		Map<String,String> fmap = new LinkedHashMap<String,String>();
		fmap.put("/user","anon");
		fmap.put("/login.html","anon");
		fmap.put("/js/*","anon");
		fmap.put("/img/*","anon");
		fmap.put("/css/*","anon");
		fmap.put("/fonts/*","anon");
		fmap.put("/reg.html", "anon");
		fmap.put("/logout","logout");
		fmap.put("/**","authc");
		bean.setFilterChainDefinitionMap(fmap);
		return bean;
	}
	
	/**
	 * 	安全管理器
	 * @param realm
	 * @return
	 */
	@Bean(name="defaultWebSecurityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm")MyRealm realm) {
		DefaultWebSecurityManager securityManage = new DefaultWebSecurityManager();
		securityManage.setRealm(realm);
		return securityManage;
	}
	
	/**
	 * 	安全
	 * @return
	 */
	@Bean(name="hashMatcher")
	public HashedCredentialsMatcher getHashMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
	
	
	@Bean("myRealm")
	public MyRealm getMyRealm(@Qualifier("hashMatcher")HashedCredentialsMatcher hashMatcher) {
		MyRealm realm = new MyRealm();
		realm.setCredentialsMatcher(hashMatcher);
		return realm;
	}
}
