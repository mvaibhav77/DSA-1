
public class HelloGoodbye {
  public static void main(String[] args) {
      String s = "";
      for(int i=0; i<args.length; i++) {
          s += args[i] + " ";
      }

      if(!s.equals("")) {
        System.out.println("Hello " + s);
        System.out.println("Goodbye " + s);

      }else{
        System.out.println("Please give name inputs...");
      }


  }
}
