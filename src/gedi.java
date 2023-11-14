import com.intersystems.jdbc.IRIS;
import com.intersystems.jdbc.IRISConnection;
import java.sql.DriverManager;
import java.util.Scanner;

public class gedi {
	public static IRIS iris; 
	public static String gbl = "^dc.MultiD" ;
    public static int def = 0;
	public static String val = "";
	public static String ZR = "" ;
	public static String sublist = "";
	public static String[] sub = new String[]{} ;
	public static String skp = "" ;
	
	public static void main(String[] args) {
		System.out.println("\n\tWelcome to IRIS NativeAPI Global Editor\n") ;
	// init connection
		String ip   = cmd("serverIP","127.0.0.1") ;
		String port = cmd("serverPORT","1972") ;
		String nspc = cmd("namespace","USER") ;
		String user = cmd("username","_SYSTEM") ;
		String pwd  = cmd("password","SYS") ;
	// get connected
	 try {
	IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://"+ip+":"+port+"/"+nspc,user,pwd);
    iris = IRIS.createIRIS(conn) ;

    System.out.println("\n\tConnected to Namespace "+nspc+" on Server "+ip+":"+port+"\n") ;
    String demo = "0";
	def = iris.isDefined(gbl,(Object[])sub);
// the main loop   
    while (demo.length()>0) {
      demo = menue();
      }
// game over  
    String ok = cmd("bye","") ;
    iris.close();
    conn.close();
    System.out.println("\nThank you for trying the demo\n");
		}
  catch (Exception ex) {
          System.out.println("Exception -- "+ex.getMessage());
    }
    return ;
	}
// ask user	
	public static String cmd( String what, String deflt) {
		Scanner sc = new Scanner(System.in);
		if (deflt.length()>0) what += " ["+deflt+"]" ;
		System.out.print(">>> "+what+": ") ;
		String ans = sc.nextLine() ;
        if (ans.isEmpty()) {
			ans=deflt ;
			}
		return ans ;
	}
	public static String format(String val) {
		String res = iris.classMethodString("%Utility","FormatString",val) ;
		return res ;
		}
	static String show (String res, boolean zw) {
		def = iris.isDefined(gbl,(Object[])sub);
		if (def%2 > 0)  {
			val=iris.getString(gbl,(Object[])sub);
			res += " = "+ format(val) ;
		}
	    if (zw) return res;
		if (def < 0 ) res += " is not defined"  ;
		else {
			if (def > 1 ) res += " has subnodes " ;
			}
		return res ;
	}
	static void setZR(String skp) {
		ZR = gbl;
		sublist = skp ;
		if (skp.length()<1) sub = new String[]{} ;
		else {
			ZR = ZR +"("+skp+")" ;
			skp=skp.replace("\"", "") ;
			sub=skp.split(",") ;
			}
		return ;
		}
	static void trimZR(String res) {
		if (res.length()<2) {
			res = gbl ;
			sub = new String[]{} ;
			sublist="" ;
			ZR = gbl;
			def = iris.isDefined(gbl,(Object[])sub);
			return ;
			}
		int fm = res.indexOf("(") + 1;	
		int to = res.indexOf(")");
		skp = "";
		if (fm < to) skp=res.substring(fm,to) ; 
		setZR(skp) ;
		return;
		}	
	// demo menue + exercise
	static String menue() {
		System.out.println("\n 0 = Select Global") ;
		System.out.println(" 1 = Select Subscripts") ;
		System.out.println(" 2 = Query Forward") ;
		System.out.println(" 3 = Query Reverse") ;	
		System.out.println(" 4 = Show Global Tree") ;	
		System.out.println(" 5 = Update Global Value") ;
		System.out.println(" 6 = Delete Global Value") ;
		System.out.println(" 7 = Delete Global Tree") ;
		System.out.println(" * = Exit Demo") ;	
		Scanner sc = new Scanner(System.in);
		String ans = sc.nextLine() ;
		String res=" ? ? ? " ;
		switch (ans){
                case "0": 
                        gbl = cmd("Global name",gbl) ;
						if (!gbl.substring(0,1).equals("^")) gbl = "^"+gbl;
						if (!gbl.matches("\\^[a-zA-Z][a-zA-Z0-9.]+")) break;
						sublist="" ;
						setZR(sublist) ;
						res = show(gbl,false) ;
						ZR = gbl ;	 					
						break;                
                case "1":
						sublist = cmd("Global subscripts list", sublist) ;
						setZR(sublist) ;
						res = show(ZR,false) ;
                        break;
                case "2":
                        res = iris.classMethodString("%SYS.Java","QueryF") ;
						trimZR(res);						                          
                        break;
                case "3": 
                        res = iris.classMethodString("%SYS.Java","QueryR") ;
						trimZR(res);						
                        break;
                case "4":
						res = show(ZR,true) ;
						String msk = ZR.substring(0,ZR.length()-1); 
						while (res.length()>1) {
							System.out.println("\t"+res) ;
							res = iris.classMethodString("%SYS.Java","QueryF") ;
							if (!res.contains(msk)) break;
							}
                        res="**** done ****" ;
                        break;
				case "5":
						val=iris.getString(gbl,(Object[])sub) + "";
						res = cmd("Update Global Value",val) ;
						iris.set(res, gbl, (Object[]) sub);
						res = show(ZR,false) ;
						break ;
                case "6": 
						skp = iris.classMethodString("%SYS.Java","ZKill") ;
						res = show(ZR,false) ;
                        break;
                case "7": 
						iris.kill(gbl,(Object[]) sub);
						res = show(ZR,false) ;
                        break;
				case "*": 
                        ans="" ;
                        res="" ;
                        break;
               default: 
                        res="Demo "+ans+" not implemented" ; 
                        ans="??" ;
                        break; 
        }
		if (res.length() >0) System.out.println("\t"+res) ;
		return ans ;
	}

}
