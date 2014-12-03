package vm;

public class GenerateVectorMatrices {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]); 
		//int p = Integer.parseInt(args[2]);
		
		// A matrix
		int element=1;		
		for (int i=1; i <= m; i++){
			StringBuilder sb = new StringBuilder();
			sb.append("A,");
			sb.append(i);
//			sb.append(",");			
			for (int j=0; j < n; j++){				
				sb.append(",");
				sb.append(element);
				element++;
			}
			System.out.println(sb.toString());
		}
		
		// B Matrix - identity matrix
		
		//element=0;		
		for (int j=1; j <= n; j++){
			StringBuilder sb = new StringBuilder();
			sb.append("B,");
			sb.append(j);
			for (int j2=1; j2 <= n; j2++){				
				sb.append(",");
				sb.append((j==j2?1:0));
				
				
			}
			System.out.println(sb.toString());
		}			

		
	}

}
