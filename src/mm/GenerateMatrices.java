package mm;

public class GenerateMatrices {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]); 
		int p = Integer.parseInt(args[2]);
		
		// A matrix
		float element=0;		
		for (int i=0; i < m; i++){
			for (int j=0; j < n; j++){				
				System.out.println("A,"+i+","+j+","+element);
				element++;
			}
		}
		
		// B Matrix
		
		element=0;		
		for (int j=0; j < n; j++){
			for (int k=0; k < p; k++){				
				System.out.println("B,"+j+","+k+","+element);
				element++;
			}
		}			

		
	}

}
