import java.util.HashMap;

public class Schedule {
	private final HashMap<Integer, Room> rooms;
    private final HashMap<Integer, Teacher> teachers;
    private final HashMap<Integer, Module> modules;
    private final HashMap<Integer, Student> students;
    private final HashMap<Integer, Block> blocks;
    private Class classes[];
    
    private int numClasses = 10;
    private int[] moduleArray = {1,1,1,2,2,3,4,5,5,6};
	double studentScore = 0;

    
    public Schedule() {
        this.rooms = new HashMap<Integer, Room>();
        this.teachers = new HashMap<Integer, Teacher>();
        this.modules = new HashMap<Integer, Module>();
        this.students = new HashMap<Integer, Student>();
        this.blocks = new HashMap<Integer, Block>();
    }
    
    public Schedule(Schedule cloneSchedule) {
        this.rooms = cloneSchedule.getRooms();
        this.teachers = cloneSchedule.getTeachers();
        this.modules = cloneSchedule.getModules();
        this.students = cloneSchedule.getStudents();
        this.blocks = cloneSchedule.getBlocks();
    }
    
    public HashMap<Integer, Room> getRooms() {
        return this.rooms;
    }
    
    public Room getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
        	System.out.println("Rooms doesn't contain key " + roomId); 
        }
        return (Room) this.rooms.get(roomId);
    }
    
    public Room getRandomRoom() {
        Object[] roomsArray = this.rooms.values().toArray();
        Room room = (Room) roomsArray[(int) (roomsArray.length*Math.random())];
        return room;
    }

    
    private HashMap<Integer, Teacher> getTeachers() {
        return this.teachers;
    }
    
    public Teacher getTeacher(int teacherId){
    	return (Teacher) this.teachers.get(teacherId);
    }
    
    private HashMap<Integer, Module> getModules(){
    	return this.modules;
    }
    
    public Module getModule(int moduleId) {
        return (Module) this.modules.get(moduleId);
    }
    
    public int[] getStudentModules(int studentId) {
        Student student = (Student) this.students.get(studentId);
        return student.getPreferredClasses();
    }
    
    private HashMap<Integer, Student> getStudents() {
        return this.students;
    }
    
    public Student getStudent(int studentId){
    	return (Student) this.students.get(studentId);
    }
    
    public Student[] getStudentAsArray(){
    	return (Student[]) (this.students.values().toArray(new Student[this.students.size()]));
    }
    

    private HashMap<Integer, Block> getBlocks(){
    	return this.blocks;
    }
    
    public Block getBlock(int blockId){
    	return (Block) this.blocks.get(blockId);
    }
    
    public Block getRandomBlock(){
    	return (Block) this.blocks.values().toArray()[(int)(blocks.size()*Math.random())];
    }
    
    public void addRoom(int roomId, String roomName, int capacity, boolean isLab) {
        this.rooms.put(roomId, new Room(roomId, roomName, capacity, isLab));
    }
    
    public void addTeachers(int teacherId, String teacherName) {
        this.teachers.put(teacherId, new Teacher(teacherId, teacherName));
    }
    
    public void addModule(int moduleId, String moduleCode, String
    	       module, boolean isLab, int teachersIds[]) {
    	       this.modules.put(moduleId, new Module(moduleId, moduleCode, module, isLab, teachersIds));
    }
    
    public void addStudent(int studentId, int studentSchoolId, String lastName, String firstName, int grade, int preferredClasses[]) {
        this.students.put(studentId, new Student(studentId, studentSchoolId, lastName, firstName, grade, preferredClasses));
        this.numClasses = 0;
    }
    
    public void addBlock(int blockId, String blockTime) {
        this.blocks.put(blockId, new Block(blockId, blockTime));
    }
    
    public Class[] getClasses(){
    	return this.classes;
    }
    
    public int getNumClasses() {
        /*if (this.numClasses > 0) {
        	return this.numClasses;
        }
        int numClasses = 0;
        Student students[] = (Student[]) this.students.values().toArray(new
        Student[this.students.size()]);
        for (Student student : students) {
               numClasses += student.getPreferredClasses().length;
        }
        this.numClasses = numClasses;
        return this.numClasses;*/
    	return numClasses;
    }
    
    public void createClasses(Individual individual) {
    	Class classes[] = new Class[this.getNumClasses()];
    	int chromosome[] = individual.getChromosome();
    	int cI = 0; //class index
		for(int i=0; i<numClasses; i++){
			//module array [111,22,333,4,55,666,7]
			//as in eng01,eng02,eng03,math01,math02,sci01,sci02,sci03
			classes[cI] = new Class(cI, moduleArray[i]);
			classes[cI].addClassFromGenes(chromosome[(int)(3*cI)], chromosome[(int)(3*cI+1)], chromosome[(int)(3*cI+2)]);
			cI++;
		}
		
		/*
    	for(Student student : this.getStudentAsArray()){
    		int[] preferredClasses = student.getPreferredClasses();
    		for(int moduleId : preferredClasses){
    			classes[cI] = new Class(cI, moduleId);
    			classes[cI].addClassFromGenes(chromosome[(int)(3*cI)], chromosome[(int)(3*cI+1)], chromosome[(int)(3*cI+2)]);
    			cI++;
    		}
    	}
    	this.classes = classes;
    	*/
    }
    
    public double calculateScheduleScore(){
    	int clashes = 0;
    	double scheduleScore = 0;
    	int studentClashes = 0; //not reach 7
    	for (Class classA : this.classes) {
    		for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId() && classA.getBlockId() == classB.getBlockId() && classA.getClassId() != classB.getClassId()){
                	clashes++;
                    break; 
                }
            }
	    	for (Class classB : this.classes) {
	    		if (classA.getTeacherId() == classB.getTeacherId() && classA.getBlockId() ==classB.getBlockId() && classA.getClassId() != classB.getClassId()) {
	    	       clashes++;
	    	       break;
	    		}
	    	}
	    	if(modules.get(classA.getModuleId()).getIsLab()){
	    		if(!rooms.get(classA.getRoomId()).getIsLab()){
	    			clashes++;
	    		}
	    	}
    	}
    	Student[] sA = (Student[])students.values().toArray();
    	for(Student s: sA){
    		int n = s.numberFit(classes);
    		if(n == 7){
    			
    		}else{
    			studentClashes++;
    		}
    		studentScore += n;
    	}
    	scheduleScore = (1/(double)(clashes+1))*100;
    	studentScore = ((int)(studentScore/(double)sA.length)*10000)/100;
    	return scheduleScore*0.7+studentScore*0.3;
    }
    
}
