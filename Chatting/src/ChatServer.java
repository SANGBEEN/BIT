
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class ChatServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10001);
			BufferedReader br = null;
			System.out.println("접속을 기다립니다.");
			HashMap<String,Object> hm1 = new HashMap<>();
			HashMap<String,Object> hm2 = new HashMap<>();
			HashMap<String,Object> hm3 = new HashMap<>();
			 
			Room room1 = new Room();
			Room room2 = new Room();
			Room room3 = new Room();

			while(true){
				Socket socket = server.accept();
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String id = br.readLine();
				int rnum=Integer.parseInt(br.readLine());
				if(rnum==1){
					room1.in(socket, hm1, id);
				}else if(rnum==2){
					room1.in(socket, hm2, id);
				}else{
					room1.in(socket, hm3, id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ChatThread extends Thread{
		private Socket socket;
		private String id;
		private BufferedReader br;
		private HashMap<String,Object> hm;
		private boolean initFlag = false;
		public ChatThread(Socket socket, HashMap hm, String id) {
			super();
			this.socket = socket;
			this.hm = hm;
			try{
				PrintWriter pw = new PrintWriter(
						new OutputStreamWriter(socket.getOutputStream()));
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				this.id = id;
				broadcast(id+"님이 접속하였습니다.");
				System.out.println("접속한 사용자의 아이디는 "+id+"입니다.");
				synchronized (hm) {
					hm.put(this.id, pw);
				}
				
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
		
		@Override
		public void run() {
			try {
				String line = null;
				while((line=br.readLine())!=null){
					if(line.equals("/quit"))
						break;
					if(line.indexOf("/to")==0){
						sendmsg(line);
					}else
						broadcast(id+" : "+line);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}finally {
				synchronized (hm) {
					hm.remove(id);
				}
				broadcast(id+"님이 접속을 종료하였습니다.");
				//배열만들어서 해쉬맵 요소 출력
				try{
					if(socket!=null)
							socket.close();
				}catch(Exception ex){}
				
			}
	
		}
		
		public void sendmsg(String msg){
			int start = msg.indexOf(" ")+1;
			int end = msg.indexOf(" ", start);
			if(end!=-1){
				String to = msg.substring(start, end);
				String msg2 = msg.substring(end+1);
				Object obj = hm.get(to);
				if(obj!=null){
					PrintWriter pw = (PrintWriter)obj;
					pw.println(id+"님이 귓속말을 보내셨습니다.:"+msg2);
					pw.flush();
				}
			}
		}
		
		public void broadcast(String msg){
			synchronized (hm) {
				Collection collection = hm.values();
				Iterator iter = collection.iterator();
				while(iter.hasNext()){
					PrintWriter pw = (PrintWriter)iter.next();
					pw.println(msg);
					pw.flush();
				}
			}
		}
	}
class Room{

	public void in(Socket socket,HashMap<String, Object> hm,String id){
		ChatThread chatthread = new ChatThread(socket, hm, id);
		chatthread.start();
	}
}

