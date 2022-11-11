package com.system.aShiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.aShiro.bean.Account;
import com.system.aShiro.mapper.AccountMapper;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private AccountMapper accountService;

	// 授權
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    // 获取当前登录对象
	    Subject subject = SecurityUtils.getSubject();
	    Account account = (Account) subject.getPrincipal();

	    // 设置角色
	    Set<String> roles = new HashSet<>();
	    roles.add(account.getRole());
	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

	    // 设置权限
	    info.addStringPermission(account.getPerms());
	    return info;
	}

	// 認證
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
	        throws AuthenticationException {

	    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	    String username = token.getUsername();
	    Account account = accountService.findByUsername(username);

	    if (account != null) {
	        return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
	    }

	    return null;

	}
}
