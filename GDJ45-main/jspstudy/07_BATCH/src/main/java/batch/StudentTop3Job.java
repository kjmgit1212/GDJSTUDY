package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Student;
import repository.StudentDAO;

public class StudentTop3Job implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
		
			List<Student> list = StudentDAO.getInstance().selectStudentTop3();
		
			File file = new File("C:\\GDJ45\\jspstudy\\07_BATCH\\시상명단.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String[] awards = {"금상", "은상", "동상"};
			for(int i = 0, length = list.size(); i < length; i++) {
				Student student = list.get(i);
				bw.write(awards[i] + " : " + student.getName() + "(" + student.getAvg() + "점)\n");
			}
			bw.close();
			System.out.println("시상명단.txt 파일이 생성되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
