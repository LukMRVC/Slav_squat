package Ostatni;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DatabaseHelper {
	MysqlDataSource source;
	Connection con;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
		public DatabaseHelper(){
		source = new MysqlDataSource();
		source.setUser("root");
		source.setPassword("lukas");
		source.setServerName("localhost");
		source.setDatabaseName("scoreboard");
	}
		//Metoda pro vlozeni dat do databaze
		public void insert (String nickname, int score){
			try {
				con = source.getConnection();
				stmt = con.createStatement();
				String sql = "INSERT into player(nickname, score) VALUES('" + nickname + "', "+ score +
						")";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error while updating database", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally{
				try { stmt.close(); } catch (SQLException e) {}
				try{ con.close(); } catch(SQLException e){}
			}
		}
		
		public boolean checkNickname(String nickname){
			try {
				//Navazani spojeni z databazi
				con = source.getConnection();
				stmt = con.createStatement();
				
				//Vytahnuti nicknamu z databaze hracu
				rs = stmt.executeQuery("SELECT nickname FROM player");
				//Ziskani prvnich deseti zaznamu
				while(rs.next()){
					if(nickname.equals(rs.getString("nickname"))){
						return true;
					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error while trying to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			//Uzavreni spojeni s databazi
			finally{
				try{  rs.close();} catch(SQLException ex){ }
				try{  stmt.close();} catch(SQLException ex){ }
				try{  con.close();} catch(SQLException ex){ }
			}
			return false;
		}
		
		
		public HashMap<String, String[]> read(){
			HashMap<String, String[]> data = new HashMap<String, String[]>();
			try {
				String[] nicknames = new String[10];
				String[] scores = new String[10];
				//Navazani spojeni z databazi
				con = source.getConnection();
				stmt = con.createStatement();
				
				//Vytahnuti prvnich deseti zaznamu z databaze hracu
				rs = stmt.executeQuery("SELECT nickname, score FROM player ORDER BY score DESC LIMIT 10");
				rsmd = rs.getMetaData();
				int i = 0;
				//Ziskani prvnich deseti zaznamu
				while(rs.next()){
					nicknames[i] = rs.getString("nickname");
					scores[i] = rs.getString("score");
					++i;
				}
			data.put("nickname", nicknames);
			data.put("score", scores);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error while trying to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			//Uzavreni spojeni s databazi
			finally{
				try{  rs.close();} catch(SQLException ex){ }
				try{  stmt.close();} catch(SQLException ex){ }
				try{  con.close();} catch(SQLException ex){ }
			}

			return data;
		}
	
}
