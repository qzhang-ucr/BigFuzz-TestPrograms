public class reduceByKey5 { 
   public static void main(String[] args) { 
       int[] arr = ;
       applyReduce(arr);
   }
 static int applyReduce( int[] a) {
   int s = a[0];
   for(int i = 1 ; i < 2 ; i++){
       s = apply( s , a[i] );
   }
   return s;
}
 static final reduceByKey5 apply(int x_t1, int x_t2,int y_t1, int y_t2){
  return new scala.reduceByKey5.mcII.sp(x_t1 + y_t1(),x_t2 + y_t2);
}
String sa,sb;

int ia,ib;
public int _1(){
	return ia;
}
public int _2(){
	return ib;
}
public reduceByKey5(int k, int v){
        ia = k;
        ib = v;
}
public reduceByKey5(String k, int v){
        sa = k;
        ib = v;
}
public reduceByKey5(int k, String v){
        ia = k;
        sb = v;
}
public reduceByKey5(String k, String v){
        sa = k;
        sb = v;
}}