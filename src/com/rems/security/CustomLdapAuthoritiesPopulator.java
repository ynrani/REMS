package com.rems.security;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.rems.constant.AppConstant;
import com.rems.dao.RemsAdminDAO;
import com.rems.model.DO.TdmUserDO;
import com.rems.model.DTO.TdmUserDTO;
import com.rems.service.RemsAdminService;
import com.rems.util.JDBCPreparedStatementSelect;

@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator
{

	private EntityManagerFactory factoryUser;
	JDBCPreparedStatementSelect jd = null;

	TdmUserDTO dto = null;
	RemsAdminService tdmAdminService = null;
	RemsAdminDAO tdmAdmindao = null;
	TdmUserDO userdo = new TdmUserDO();

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {

		String role = "", username_and_role = "";
		jd = new JDBCPreparedStatementSelect();
		try {
			username_and_role = jd.selectRecordsFromTable(username);
			StringTokenizer st = new StringTokenizer(username_and_role, "-");
			while (st.hasMoreTokens()) {
				username = st.nextToken();
				role = st.nextToken();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();

		if (StringUtils.isNotEmpty(role) && role.equals(AppConstant.ROLE_USER)) {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_USER));
		} else if (StringUtils.isNotEmpty(role) && role.equals(AppConstant.ROLE_ENVOWNER)) {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_ENVOWNER));
		} else if (StringUtils.isNotEmpty(role) && role.equals(AppConstant.ROLE_ADMIN)) {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_ADMIN));
		} else if (StringUtils.isNotEmpty(role) && role.equals(AppConstant.ROLE_TDMUSER)) {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_TDMUSER));
		} else if (StringUtils.isNotEmpty(role) && role.equals(AppConstant.ROLE_TDMADMIN)) {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_TDMADMIN));
		} else {
			gas.add(new SimpleGrantedAuthority(AppConstant.ROLE_INVALID));
		}

		return gas;
	}
}
