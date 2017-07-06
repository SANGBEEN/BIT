package kr.co.bit.member.vo;

public class MemberVO
{
  private String id;
  private String password;
  private String name;
  private String emailId;
  private String emailDomain;
  private String tel1;
  private String tel2;
  private String tel3;
  private String basicAddr;
  private String detailAddr;
  private String type;
  private String regDate;
  private String post;
  
  public MemberVO() {}
  
  public MemberVO(String id, String password, String name, String emailId, String emailDomain, String tel1, String tel2, String tel3, String basicAddr, String detailAddr, String type, String regDate, String post)
  {
    this.id = id;
    this.password = password;
    this.name = name;
    this.emailId = emailId;
    this.emailDomain = emailDomain;
    this.tel1 = tel1;
    this.tel2 = tel2;
    this.tel3 = tel3;
    this.basicAddr = basicAddr;
    this.detailAddr = detailAddr;
    this.type = type;
    this.regDate = regDate;
    this.post = post;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPost()
  {
    return this.post;
  }
  
  public void setPost(String post)
  {
    this.post = post;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getEmailDomain()
  {
    return this.emailDomain;
  }
  
  public void setEmailDomain(String emailDomain)
  {
    this.emailDomain = emailDomain;
  }
  
  public String getTel1()
  {
    return this.tel1;
  }
  
  public void setTel1(String tel1)
  {
    this.tel1 = tel1;
  }
  
  public String getTel2()
  {
    return this.tel2;
  }
  
  public void setTel2(String tel2)
  {
    this.tel2 = tel2;
  }
  
  public String getTel3()
  {
    return this.tel3;
  }
  
  public void setTel3(String tel3)
  {
    this.tel3 = tel3;
  }
  
  public String getBasicAddr()
  {
    return this.basicAddr;
  }
  
  public void setBasicAddr(String baiscAddr)
  {
    this.basicAddr = baiscAddr;
  }
  
  public String getDetailAddr()
  {
    return this.detailAddr;
  }
  
  public void setDetailAddr(String detailAddr)
  {
    this.detailAddr = detailAddr;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getRegDate()
  {
    return this.regDate;
  }
  
  public void setRegDate(String regDate)
  {
    this.regDate = regDate;
  }
  
  public String toString()
  {
    return 
    
      "MemberVO [id=" + this.id + ", password=" + this.password + ", name=" + this.name + ", emailId=" + this.emailId + ", emailDomain=" + this.emailDomain + ", tel1=" + this.tel1 + ", tel2=" + this.tel2 + ", tel3=" + this.tel3 + ", basicAddr=" + this.basicAddr + ", detailAddr=" + this.detailAddr + ", type=" + this.type + ", regDate=" + this.regDate + ", post=" + this.post + "]";
  }
}
