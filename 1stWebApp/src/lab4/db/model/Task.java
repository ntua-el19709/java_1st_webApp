package lab4.db.model;

import java.util.Date;

public class Task {
	final Integer taskId;
	final Integer userId;
	final Date startDate;//Date updated
	final String comments;//description
	final String title;
	final Integer status;
	
	public Task(Integer taskId,Integer userId, Date startDate,String title, String comments,Integer Status) {
		super();
		this.taskId=taskId;
		this.userId = userId;
		this.startDate = startDate;
		this.title=title;
		this.comments = comments;
		this.status=Status;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public Integer getUserId() {
		return userId;
	}
	public Integer getStatus() {
		return status;
	}


	public Date getStartDate() {
		return startDate;
	}
	
	public String getTitle() {
		return title;
	}


	public String getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "Reservation [userId=" + userId + ", productId=" +", startDate=" + startDate + ", endDate="
				+ ", comments=" + comments + "]";
	}
	
}
