public class map2 { 
   public static void main(String[] args) { 
       apply();
   }
  static final Tuple3 apply(String s){
  String cols[]=s.split(",");
  return new Tuple3(cols[0],(Integer.parseInt(cols[1])),(Integer.parseInt(cols[2])));
}
}