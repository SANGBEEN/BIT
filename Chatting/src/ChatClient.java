
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("���� : java CharClient ip ");
		}
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		boolean endflag = false;
		try {
			socket = new Socket(args[0], 10001);
			pw = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(
					new InputStreamReader(System.in));
			
			//����� ���̵� ����
			System.out.println("���̵� �Է��ϼ���");
			pw.println(keyboard.readLine());
			System.out.println("���ȣ");
			pw.println(keyboard.readLine());
			//pw.print(args[0]);
			pw.flush();
			
			InputThread it = new InputThread(socket,br);
			it.start();
			String line = null;
			while((line=keyboard.readLine())!=null){
				pw.println(line);
				pw.flush();
				if(line.equals("/quit")){
					endflag = true;
					break;
				}
				
			}
			System.out.println("Ŭ���̾�Ʈ�� ������ �����մϴ�.");
		} catch (Exception ex) {
			if(!endflag){
				ex.printStackTrace();
			}
		}finally{
			try{
				if(pw!=null)
					pw.close();
			}catch(Exception ex){}
			try {
				if(br!=null)
					br.close();
			} catch (Exception ex) {}
			try{
				if(socket!=null)
					socket.close();
			}catch(Exception ex){}
		}
	}
}
class InputThread extends Thread{
		private Socket socket = null;
		private BufferedReader br = null;
		public InputThread(Socket socket, BufferedReader br) {
			super();
			this.socket = socket;
			this.br = br;
		}
		@Override
		public void run() {
			try {
				String line = null;
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
			} catch (Exception e) {
			} finally {
				try{
					if(br!=null)
						br.close();
				}catch(Exception e){}
				try{
					if(socket!=null)
						socket.close();
				}catch(Exception e){}
			}
		}
		
	}

