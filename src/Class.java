
public class Class {
	private final int classId;
	//private final int studentId;
	private final int moduleId;
	private int teacherId;
	private int blockId;
	private int roomId;
	
	public Class(int classId/*, int studentId*/, int moduleId) {
		this.classId = classId;
		this.moduleId = moduleId;
		//this.studentId = studentId;
	}
	
	public void addTeacher(int teacherId) {
		this.teacherId = teacherId;
	}
	
	public void addBlock(int blockId) {
		this.blockId = blockId;
	}
	
	public void addClassFromGenes(int blockId, int roomId, int teacherId){
		this.blockId = blockId;
		this.roomId = roomId;
		this.teacherId = teacherId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public int getClassId() {
		return this.classId;
	}
	
	/*public int getStudentId() {
		return this.studentId;
	}*/

	public int getModuleId() {
		return this.moduleId;
	}
	
	public int getTeacherId() {
		return this.teacherId;
	}
	
	public int getBlockId() {
		return this.blockId;
	}
	
	public int getRoomId() {
		return this.roomId;
	}
}
