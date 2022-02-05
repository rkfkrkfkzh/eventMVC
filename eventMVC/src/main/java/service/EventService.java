package service;

import java.util.ArrayList;

import model.Dao;
import model.Member;
import model.OracleDao;

public class EventService implements Service {

	private Dao dao;

	public EventService() {
		dao = OracleDao.getDao();
	}

	@Override
	public void insert(Member m) {
		// TODO Auto-generated method stub
		dao.insert(m);
	}

	@Override
	public void edit(Member m) {
		// TODO Auto-generated method stub
		dao.update(m);
	}

	@Override
	public ArrayList<Member> list() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		dao.delete(num);
	}

	@Override
	public Member getMember(int num) {
		// TODO Auto-generated method stub
		return dao.getMember(num);
	}

}
