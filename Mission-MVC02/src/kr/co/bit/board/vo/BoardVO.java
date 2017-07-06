package kr.co.bit.board.vo;

public class BoardVO
{
  private int no;
  private String title;
  private String writer;
  private String content;
  private int viewCnt;
  private String regDate;
  
  public BoardVO() {}
  
  public BoardVO(int no, String title, String writer, String content, int viewCnt, String regDate)
  {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.viewCnt = viewCnt;
    this.regDate = regDate;
  }
  
  public int getNo()
  {
    return this.no;
  }
  
  public void setNo(int no)
  {
    this.no = no;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getWriter()
  {
    return this.writer;
  }
  
  public void setWriter(String writer)
  {
    this.writer = writer;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public int getViewCnt()
  {
    return this.viewCnt;
  }
  
  public void setViewCnt(int viewCnt)
  {
    this.viewCnt = viewCnt;
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
      "BoardVO [no=" + this.no + ", title=" + this.title + ", writer=" + this.writer + ", content=" + this.content + ", viewCnt=" + this.viewCnt + ", regDate=" + this.regDate + "]";
  }
}
