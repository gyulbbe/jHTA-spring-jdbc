package jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jdbc.vo.Job;

@Repository
public class JobDao {

	@Autowired
	private JdbcTemplate t;
	
	public void insertJob(Job job) {
		String sql = """
				insert into jobs
				(job_id, job_title, min_salary, max_salary)
				values
				(?, ?, ?, ?)
				""";
		
		t.update(sql,
				job.getId(),
				job.getTitle(),
				job.getMinSalary(),
				job.getMaxSalary());
	}
	
	public List<Job> getAllJobs() {
		String sql = """
				select *
				from jobs
				order by job_id asc
				""";
		/*
		return t.query(sql, new RowMapper<Job>() {
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();
				job.setId(rs.getString("job_id"));
				job.setTitle(rs.getString("job_title"));
				job.setMinSalary(rs.getInt("minx_salary"));
				job.setMaxSalary(rs.getInt("max_salary"));
				
				return job;
			}
		});
		*/
		
		return t.query(sql, (rs, rowNum) -> {
			Job job = new Job();
			job.setId(rs.getString("job_id"));
			job.setTitle(rs.getString("job_title"));
			job.setMinSalary(rs.getInt("min_salary"));
			job.setMaxSalary(rs.getInt("max_salary"));
			
			return job;
		});
	}
	
	public Job getJobById(String id) {
		String sql = """
				select *
				from jobs
				where job_id = ?
				""";
		
		return t.queryForObject(sql, (rs, rowNum) -> {
			return null;
		}, id);
	}
	
	public void updateJob(Job job) {
		String sql = """
				update jobs
				set
					job_title = ?,
					min_salary = ?,
					max_salary = ?
				where job_id = ?
				""";
		
		t.update(sql,
				job.getTitle(),
				job.getMinSalary(),
				job.getMaxSalary(),
				job.getId());
	}
}
