package Server;

import java.sql.*;
import java.io.*; 
import java.net.*; 
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server 
{ 
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{ 
	
		ServerSocket ss = new ServerSocket(5056);
               
                while (true) 
		{ 
			Socket s = null; 
			 
			try
			{ 
				// GELEN CLİENTLARIN KABUL ETMEYE BAŞLAR
                                System.out.println("OYUN BAŞLATILDI.OYUNCULAR BEKLENİYOR...");
				s = ss.accept();
				
				// VERİ ALMAK İÇİN GEREKLİ İNPUT VE OUTPUT NESNELERİ OLUŞTURULDU
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                               
                               String isim_getir=dis.readUTF();
                                System.out.println("Yeni Kullanıcı Baglandı : " +isim_getir); 
				
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datacom","root","");
                                
                                Statement stmt3=con.createStatement(); 
                                String sorgu3=String.format("insert into kullanicilar(kullanici_adi,durum,puan) values('%s',%d,%d)",isim_getir,1,0);
                                stmt3.executeUpdate(sorgu3);
                                
                                // Theared Obl-jesi oluşturuyoruz
				Thread t = new ClientHandler(s, dis, dos); 
				t.start(); 
			} 
			catch (IOException | SQLException e){ 
				s.close(); 
			} 
		} 
	} 

}





// ***************************************************************Multi Client******************************************************



class ClientHandler extends Thread 
{ 
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s;  
        ClientHandler clientshandler;
    
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws SQLException 
	{ 
        
		this.s = s; 
		this.dis = dis; 
		this.dos = dos;  
        } 

	@Override
	public void run() 
	{ 
            try { 
               
               
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datacom","root","");
             
                /*Sorular kısmında id 1 olan sorulan cevaplanmış sorular id 0 olanlar çağırılmamış olanlar*/
                
               //String sorgu=String.format("select * from sorular where durumu=0 order by rand() limit 1 ") ;
              String sorgu=String.format("select * from sorular") ;
               Statement stmt=con.createStatement();    
               ResultSet rs=stmt.executeQuery(sorgu); 
 
               String[] sorular=new String[5];
               String[] cevaplar=new String[5];
              
               int a=0;  
               while(rs.next()){  
               sorular[a]=rs.getString(2);
               cevaplar[a]=rs.getString(3);
               //sorular.add(rs.getString(2));
               //cevaplar.add(rs.getString(3));
              // String guncelle=String.format("update sorular set durumu=%d where id=%d ",1,rs.getInt("id")) ;
               //Statement stmt2=con.createStatement(); 
               //stmt2.executeUpdate(guncelle); 
               a=a+1;
                }
               
                int i=0;
                String received = null; 
                
               // synchronized(clientshandler){ 
                while(true)
                {        
                    
                    dos.writeUTF(sorular[i]);
                    // Cevabı Değerlendirme
                    received = dis.readUTF();
                  //  wait();
                    String sorgu7=String.format("SELECT COUNT(*) FROM kullanicilar where durum='1'") ;
                    Statement stmt7=con.createStatement();    
                    ResultSet rs7=stmt7.executeQuery(sorgu7); 
                    rs7.next();
                    int clientsayisi=rs7.getInt(1);
                   
                    
                    if(!cevaplar[i].equals(received)){
                       dos.writeUTF("Kaybettiniz");
                       String name2=dis.readUTF();
                       
                       TimeUnit.SECONDS.sleep(1);
                       Statement stmt6=con.createStatement(); 
                       String sorgu6=String.format("update kullanicilar set durum=0 where kullanici_adi='%s' ",name2) ;
                       stmt6.executeUpdate(sorgu6);
                       
                       System.out.println(name2 + " Oyunu Kaybetti");
                       this.s.close();
                        break;
                    }
                  
                    
                    
                    if(clientsayisi==0 || clientsayisi==1){
                         String sorgu8=String.format("SELECT * FROM kullanicilar where puan in(SELECT MAX(puan) from kullanicilar) and durum='1' ") ;
                         Statement stmt8=con.createStatement();    
                         ResultSet rs8=stmt8.executeQuery(sorgu8); 
                         rs8.next();    
                         dos.writeUTF("Kazandın");   
                         System.out.println("OYUNU KAZANAN OYUNCUMUZ "+rs8.getString(2));
                         System.out.println("Puanınız "+ rs8.getString(4));
                         this.s.close();
                         break;
                    }
                    else{
                    dos.writeUTF("Tebrikler 5 puan kazandın");
                    i=i+1;
                 
                    String name=dis.readUTF();
                     TimeUnit.SECONDS.sleep(1);
                    
                   
                    String sorgu4=String.format("select * from kullanicilar where kullanici_adi='%s' ",name) ;
                    Statement stmt4=con.createStatement();    
                    ResultSet rs4=stmt4.executeQuery(sorgu4); 
                    rs4.next();
                   
                    int puan=5+rs4.getInt(4);
           
                    Statement stmt5=con.createStatement(); 
                    String sorgu5=String.format("update kullanicilar set puan=%d where kullanici_adi='%s' ",puan,name) ;
                    stmt5.executeUpdate(sorgu5);


                }
                }
              //  }            
                
            } catch (SQLException | ClassNotFoundException | IOException | InterruptedException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
	} 
} 
