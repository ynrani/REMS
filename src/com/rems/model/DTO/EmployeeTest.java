package com.rems.model.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rems.exception.ServiceException;
import com.rems.model.DO.RemsBookEnviDO;
import com.rems.model.mapper.impl.RemsBookingMapperImpl;

public class EmployeeTest
{

	public static void main(String[] args) throws ParseException, ServiceException {
		// TODO Auto-generated method stub
		Set<Employee> list = new HashSet<Employee>();
		Employee e = new Employee();
		e.setId(1);
		e.setName("venkat");
		Employee e1 = new Employee();
		e1.setId(11);
		e1.setName("1venkat");
		Employee e3 = new Employee();
		e3.setId(1);
		list.add(e1);
		list.add(e);
		System.out.println(list);
		if (list.contains(e3))
			System.out.println("true");

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		RemsBookEnviDO res1 = new RemsBookEnviDO();
		res1.setApplName("app1");
		res1.setEnvName("env1");
		String str1 = "02/12/2016";
		String str2 = "02/15/2016";
		res1.setStartDt(format.parse(str1));
		res1.setEndDt(format.parse(str2));

		RemsBookEnviDO res2 = new RemsBookEnviDO();
		res2.setApplName("app1");
		res2.setEnvName("env1");
		String str11 = "02/12/2016";
		String str12 = "02/20/2016";
		res2.setStartDt(format.parse(str11));
		res2.setEndDt(format.parse(str12));

		RemsBookEnviDO res21 = new RemsBookEnviDO();
		res21.setApplName("app2");
		res21.setEnvName("env2");
		String str111 = "02/12/2016";
		String str121 = "02/20/2016";
		res21.setStartDt(format.parse(str111));
		res21.setEndDt(format.parse(str121));

		RemsBookEnviDO res22 = new RemsBookEnviDO();
		res22.setApplName("app2");
		res22.setEnvName("env3");
		String str112 = "02/12/2016";
		String str122 = "02/20/2016";
		res22.setStartDt(format.parse(str112));
		res22.setEndDt(format.parse(str122));

		RemsBookEnviDO res23 = new RemsBookEnviDO();
		res23.setApplName("app3");
		res23.setEnvName("env3");
		String str113 = "02/12/2016";
		String str123 = "02/20/2016";
		res23.setStartDt(format.parse(str113));
		res23.setEndDt(format.parse(str123));

		List<RemsBookEnviDO> lst = new ArrayList<RemsBookEnviDO>();
		lst.add(res1);
		lst.add(res2);
		lst.add(res21);
		lst.add(res22);
		lst.add(res23);

		RemsBookingMapperImpl imp = new RemsBookingMapperImpl();
		System.out.println(imp.convertRemsBookEnviDOListToRemsEnvBookingsDTOFinalList(lst, null));
		// System.out.println("Total size is :"+);

	}

}
