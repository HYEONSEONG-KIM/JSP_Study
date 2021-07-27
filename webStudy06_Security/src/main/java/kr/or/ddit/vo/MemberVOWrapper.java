package kr.or.ddit.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class MemberVOWrapper extends User{
	
	private MemberVO adaptee;
	
	public MemberVOWrapper(MemberVO adaptee) {
		this(adaptee.getMemId(), adaptee.getMemPass(), AuthorityUtils.createAuthorityList(adaptee.getMemRole()));
		this.adaptee = adaptee;
	}

	public MemberVOWrapper(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	public MemberVOWrapper(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

}
