package com.nbs.iais.ms.common.db.domains.interfaces.meta;

import java.util.Date;
import java.util.List;



public interface StatisticalProcess {

	public String getAcronymEn();

	public void setAcronymEn(String acronymEn);

	public String getAcronymRo();

	public void setAcronymRo(String acronymRo);

	public String getAcronymRu();

	public void setAcronymRu(String acronymRu);

	public String getNameEn();

	public void setNameEn(String nameEn);

	public String getNameRo();

	public void setNameRo(String nameRo);

	public String getNameRu();

	public void setNameRu(String nameRu);

	public String getRespFullName();

	public void setRespFullName(String respFullName);

	public String getRespMail();

	public void setRespMail(String respMail);

	public String getRespPhone();

	public void setRespPhone(String respPhone);

	public Date getSysDate();

	public void setSysDate(Date sysDate);

	public List<GsbpmStatProc> getGsbpmStatProcs();

	public void setGsbpmStatProcs(List<GsbpmStatProc> gsbpmStatProcs);

	public List<Law> getLaws();

	public void setLaws(List<Law> laws);

	public Division getDivision();

	public void setDivision(Division division);

	public SysUser getSysUserBean();

	public void setSysUserBean(SysUser sysUserBean);

 
}