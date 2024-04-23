

public class App {

	public static void main(String[] args) {
		Machine m = new Machine(4); 
		// m.setProgram( new Set(0, 3), 
		// 		      new Set(1, 1), 
		// 		      new Set(2, -1),
				    //   new IfZero(0, 7), 
				    //   new Inc(0, 2),
				    //   new Inc(1, 1),
				    //   new IfZero(3, 3));
		new Set	(new Reg("A"),	new Reg("B"));
		new Set	(new Reg("A"),	new Mem(20));
		new Set	(new Reg("A"),	new Val(2000));
		new Set	(new Mem(10),	new Reg("B"));
		new Set	(new Mem(10),	new Mem(20));
		new Set	(new Mem(10),	new Val(2000));  
		m.printProgram();
		System.out.println();
		// do {
		// 	m.printMemory();
		// 	System.out.println();
		// } while (m.step());
		// m.printMemory();
		
        //set6.execute();

		
		
	}
	
}
