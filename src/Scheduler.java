
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
        System.out.println("Final Score: " + schedule.calculateFScheduleScore());
        System.out.println("Solution found in " + (generation-1) + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
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
            
            System.out.println();
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

		schedule.addTeachers(1, "Mr Lo");
		schedule.addTeachers(2, "Mrs Holt");
		schedule.addTeachers(3, "Mr Quemby");
		schedule.addTeachers(4, "Mr Raybaud");
		schedule.addTeachers(5, "Mr Kang");

		
		schedule.addModule(1, "cs1", "Computer Science", true, new int[] { 1, 2 });
		schedule.addModule(2, "en1", "English", false, new int[] { 1, 3, 5 });
		schedule.addModule(3, "ma1", "Maths", false, new int[] { 1, 2 });
		schedule.addModule(4, "ph1", "Physics", true, new int[] { 3, 4 });
		schedule.addModule(5, "hi1", "History", false, new int[] { 4 });
		schedule.addModule(6, "dr1", "Drama", false, new int[] { 1, 4 });
		schedule.addModule(7, "calc1", "Calculus", false, new int[] { 2, 4 });
		schedule.addModule(8, "chem1", "Chemistry", false, new int[] { 5, 4 });
		schedule.addModule(9, "bio1", "Biology", false, new int[] { 1, 4, 5 });
		schedule.addModule(10, "stats1", "Statistics", false, new int[] { 2, 3, 5 });

		
		
		schedule.addStudent(1, 802895, "Tom", "Li", 12, new int[] { 1, 2, 3, 4, 5, 6, 7});
		schedule.addStudent(2, 802806, "Tim", "Lee", 12, new int[] { 2, 3, 4, 5, 6,7,8});
		schedule.addStudent(3, 802807, "Steven", "Wong", 12, new int[] { 1, 3, 4,5,6,7,8});
		schedule.addStudent(4, 802805, "Jeffrey", "Kam", 12, new int[] { 1,2, 3, 6,7,9,10 });
		schedule.addStudent(5, 802804, "Matthew", "Lee", 12, new int[] { 1, 2, 4,5,7,8,9 });
		schedule.addStudent(6, 802803, "Harry", "Lee", 11, new int[] { 1, 2, 3, 5, 6,7,10 });
		schedule.addStudent(8, 802802, "Jack", "Nam", 11, new int[] { 2, 3, 4, 6, 8, 9, 10 });
		schedule.addStudent(9, 802891, "Jason", "Au", 10, new int[] { 1, 3, 4, 6, 7,8,10 });
		schedule.addStudent(10, 802892, "Ronald", "Ng", 12, new int[] { 1,2, 4, 5, 6, 7,8 });
		schedule.addStudent(11, 802893, "Justin", "Ko", 12, new int[] { 2, 4, 5,7,8,9,10 });
		schedule.addStudent(12, 802894, "John", "Ivanhoe", 12, new int[] { 1, 2, 4, 6,7,8,9 });
		schedule.addStudent(13, 802194, "Tony", "Kovari", 10, new int[] { 1, 2, 3, 5,6,7,9 });
		schedule.addStudent(14, 802294, "Tim", "Chen", 10, new int[] { 1, 2, 3, 6,7,9,10 });
		//for testing computational speed and power
		for(int i=15; i<350;i++){
			schedule.addStudent(i, 802294, "Tim", "Chen", 10, new int[] { 1, 2, 3, 6,7,9,10 });
		}
		
		
		return schedule;
	}
}
