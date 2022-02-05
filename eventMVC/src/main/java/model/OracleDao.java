package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OracleDao implements Dao {

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public Statement stmt = null;
	public String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	public String jdbc_url = "jdbc:oracle:thin:@db202110262237_high?TNS_ADMIN=/Users/imhyojin/Wallet_DB202110262237";
	ResultSet rs = null;

	private static OracleDao sd = new OracleDao();

	private OracleDao() {
	}

	public static OracleDao getDao() {
		return sd;
	}

	@Override
	public boolean con() {
		// TODO Auto-generated method stub
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "ADMIN", "Dkfdktek36270113");
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public void discon() {
		// TODO Auto-generated method stub
		try {
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void insert(Member m) {
		// TODO Auto-generated method stub
		con();
		String sql = "insert into event value(event_seq.nextval,?,?,?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, m.getNum());
			this.pstmt.setString(2, m.getId());
			this.pstmt.setString(3, m.getPwd());
			this.pstmt.setString(4, m.getEmail());
			this.pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			discon();
		}
	}

	@Override
	public ArrayList<Member> getAll() {
		// TODO Auto-generated method stub

		con();
		ArrayList<Member> list = new ArrayList<Member>();
		String sql = "select * from event order by num";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int num = rs.getInt(1);
				String id = rs.getString(2);
				String email = rs.getString(3);
				String pwd = rs.getString(4);
				Member m = new Member(num, id, email, pwd);
				list.add(m);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			discon();
		}
		return list;
	}

	@Override
	public Member getMember(int num) {
		// TODO Auto-generated method stub
		con();
		String sql = "select * from event where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num2 = rs.getInt("num");
				String id = rs.getString("id");
				String email = rs.getString("email");
				String pwd = rs.getString("pwd");
				Member m = new Member(num2, id, email, pwd);
				return m;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			discon();
		}
		return null;
	}

	@Override
	public void update(Member m) {
		// TODO Auto-generated method stub
		con();
		String sql = "update event set email=?, pwd=? where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getEmail());
			pstmt.setString(2, m.getPwd());
			pstmt.setInt(3, m.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			discon();
		}
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		con();
		String sql = "delete event where num=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			discon();
		}

	}
}
