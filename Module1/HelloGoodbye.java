
public class HelloGoodbye {
  public static void main(String[] args) {
      if(args.length < 2) {
        System.out.println("Please give name inputs...");
        return;
      }
      
      String s = args[0] + " " + args[1];

      if(!s.equals("")) {
        System.out.println("Hello " + s);
        System.out.println("Goodbye " + s);

      }else{
        System.out.println("Please give name inputs...");
      }


  }
}
