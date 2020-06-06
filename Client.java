package Client;
import java.io.*; 
import java.net.*; 
import java.util.Random;
import java.util.Scanner; 

// Client class 
public class Client 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		
            
            try
		{ 
			Scanner scn = new Scanner(System.in); 
			
			
			InetAddress ip = InetAddress.getByName("localhost"); 
	
			
			Socket s = new Socket(ip, 5056); 
	
			// Veri Giriş ve Çıkış Setleri 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			
			
                        Random rant=new Random(); //random sınıfı
                        int kullanicisayisi=rant.nextInt(100);
                        String name;
                        String cevap;
                        name="Client"+kullanicisayisi;    
                        dos.writeUTF(name);
                         System.out.println(name+" OYUNA BAĞLANDINIZ.");
                     
                     
                     while (true) 
			{ 
				System.out.println(dis.readUTF()); 
				String tosend = scn.nextLine(); 
				dos.writeUTF(tosend); 
				
				cevap=dis.readUTF();
                                 if(cevap.equals("Kaybettiniz")) 
				{ 
                                        System.out.println("OYUNU Kaybettiniz "); 
					dos.writeUTF(name);
                                        s.close(); 
					break; 
				}  
                                 if(cevap.equals("Kazandın")) 
				{ 
                                        System.out.println("TEBRİKLER OYUNU KAZANDIN!!!" + name); 
                                        s.close(); 
					break; 
				}
                                 else
                                     dos.writeUTF(name);
                                    System.out.println(cevap); 
                                 
				
                        } 
			
			// Bağlantıları kapatma 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(IOException e){ 
		} 
	} 
}
