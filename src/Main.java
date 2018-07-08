import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		Listener myListener = null;
		int option = 99;
		int value = 30;
		
		while (option != 0)
		{
			System.out.println("Enter option: ");
			System.out.println("0 - Exit");
			System.out.println("1 - Publish new data");
			if(myListener == null)
			{
				System.out.println("2 - Launch listener");
			}
			
			option = myScanner.nextInt();
			
			switch (option) {
			// Publish
			case 1:
				Publisher.launch(new String[] {String.valueOf(value++)});
				break;
			// Listener
			case 2:
				if(myListener == null)
				{
					myListener = Listener.launch(null);
				}
				break;
			}
		}		
	}

}

