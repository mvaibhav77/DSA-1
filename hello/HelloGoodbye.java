/* *****************************************************************************
 *  Name:              Vaibhav Shukla
 *  Coursera User ID:  cf2d493838fbf83017d21569a7082619
 *  Last modified:     July 25, 2024
 **************************************************************************** */


public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please give name inputs...");
            return;
        }

        String s = args[0] + " and " + args[1];

        System.out.println("Hello " + s);
        System.out.println("Goodbye " + s);


    }
}
