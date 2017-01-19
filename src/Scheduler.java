
public class Scheduler {
	public static void main(String[] args){
		Schedule schedule = initializeSchedule();
		GA ga = new GA(100,0.01, 0.9, 2, 5);
		int generation = 1;
		Population population = ga.initPopulation(schedule);
		//evaluate
		//evolve
		while (!ga.isTerminationConditionMet(generation, 1000) && !ga.isTerminationConditionMet(population)){
			System.out.println("Generation: " + generation + " Best Fitness: " + population.getFittest(0).getFitness());
			//crossover
			population = ga.crossoverPopulation(population);
			//mutate
			population = ga.mutatePopulation(population,schedule);
			//evaluate
			ga.evalPopulation(population, schedule);
			generation++;
		}
		//show final fitness and schedule
		schedule.createClasses(population.getFittest(0));
		System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + schedule.calculateScheduleScore());
        System.out.println();
        Class classes[] = schedule.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("Class " + classIndex + ":");
            System.out.println("Module: " + 
            		schedule.getModule(bestClass.getModuleId()).getModuleName());
            System.out.println("Room: " + 
            		schedule.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("Professor: " + 
            		schedule.getTeacher(bestClass.getTeacherId()).getteacherName());
            System.out.println("Time: " + 
            		schedule.getBlock(bestClass.getBlockId()).getBlockTime());
            System.out.println("-----");
            classIndex++;
        }
	}
	
	private static Schedule initializeSchedule() {
		Schedule schedule = new Schedule();

		schedule.addRoom(1, "101", 25, true);
		schedule.addRoom(2, "203", 25, true);
		schedule.addRoom(3, "303", 25, false);
		schedule.addRoom(4, "104", 25, false);

		schedule.addBlock(1, "Mon 9:00 - 11:00");
		schedule.addBlock(2, "Mon 11:00 - 13:00");
		schedule.addBlock(3, "Mon 13:00 - 15:00");
		schedule.addBlock(4, "Tue 9:00 - 11:00");
		schedule.addBlock(5, "Tue 11:00 - 13:00");
		schedule.addBlock(6, "Tue 13:00 - 15:00");
		schedule.addBlock(7, "Wed 9:00 - 11:00");
		schedule.addBlock(8, "Wed 11:00 - 13:00");
		schedule.addBlock(9, "Wed 13:00 - 15:00");
		schedule.addBlock(10, "Thu 9:00 - 11:00");
		schedule.addBlock(11, "Thu 11:00 - 13:00");
		schedule.addBlock(12, "Thu 13:00 - 15:00");
		schedule.addBlock(13, "Fri 9:00 - 11:00");
		schedule.addBlock(14, "Fri 11:00 - 13:00");
		schedule.addBlock(15, "Fri 13:00 - 15:00");

		schedule.addTeachers(1, "Mr Lo");
		schedule.addTeachers(2, "Mrs Holt");
		schedule.addTeachers(3, "Mr Quemby");
		schedule.addTeachers(4, "Mr Raybaud");

		schedule.addModule(1, "cs1", "Computer Science", true, new int[] { 1, 2 });
		schedule.addModule(2, "en1", "English", false, new int[] { 1, 3 });
		schedule.addModule(3, "ma1", "Maths", false, new int[] { 1, 2 });
		schedule.addModule(4, "ph1", "Physics", true, new int[] { 3, 4 });
		schedule.addModule(5, "hi1", "History", false, new int[] { 4 });
		schedule.addModule(6, "dr1", "Drama", false, new int[] { 1, 4 });

		schedule.addStudent(1, 802895, "Tom", "Li", 12, new int[] { 1, 3, 4 });
		schedule.addStudent(2, 802806, "Tim", "Lee", 12, new int[] { 2, 3, 4 });
		schedule.addStudent(3, 802807, "Steven", "Wong", 12, new int[] { 1, 3, 5 });
		schedule.addStudent(4, 802805, "Jeffrey", "Kam", 12, new int[] { 1, 3, 6 });
		schedule.addStudent(5, 802804, "Matthew", "Lee", 12, new int[] { 1, 2, 4 });
		schedule.addStudent(6, 802803, "Harry", "Lee", 11, new int[] { 1, 2, 5 });
		schedule.addStudent(8, 802802, "Jack", "Nam", 11, new int[] { 2, 3, 4 });
		schedule.addStudent(9, 802891, "Jason", "Au", 10, new int[] { 3, 4, 6 });
		schedule.addStudent(10, 802892, "Ronald", "Ng", 12, new int[] { 1, 4, 5 });
		schedule.addStudent(11, 802893, "Justin", "Ko", 12, new int[] { 2, 4, 5 });
		schedule.addStudent(12, 802894, "John", "Ivanhoe", 12, new int[] { 1, 4, 6 });
				
		return schedule;
	}
}
