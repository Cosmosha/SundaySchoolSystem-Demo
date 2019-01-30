

package sundaySch.util;

import java.io.*;

public class IPAddressing {
    
    private String sPath = "IPAddress.txt";
    
    public IPAddressing(){}

    //<editor-fold defaultstate="collapse" desc="getAddress">
    public String getAddress(){
        /**Check if the file exit. if not then use the default value */
        if (new File(sPath).getAbsoluteFile().exists()){
               //then retrieve address
            String sS = getFileContent(new File(sPath).getAbsoluteFile());
            return sS;   
        }else{
            return "127.0.0.1";
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="saveAddress">
    public void saveAddress(String sAddress){
           /** check if the file does not exist and then create it.  */
           if (!new File(sPath).getAbsoluteFile().exists()){
               try{
                    new File(sPath).createNewFile();
                }catch (Exception e){}
           }
           setOutputText(sAddress, new File(sPath).getAbsoluteFile(), false);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="setOutputText">
    /**This method is used to write text into a file. You can append or write a new
    * text. The flag in setOutputText is set to true to append and set to false for new text.
    * To write to a named file use InitializationUtility.setOutputText(outputString, new File("filename").getAbsoluteFile(), flag);
    */
    public static void setOutputText(String outputString, File f, boolean flag){
        /*First test if the file is not null then a file is selected
        PrintWriter is used to wrap BufferedWriter since PrintWriter does not throw
        any exception and it can work with empty string. It output as string always*/
        if (f != null){
            try {
                PrintWriter out; //variable to be used to write
                out = new PrintWriter(new BufferedWriter(new FileWriter(f, flag)));
                /** check the presence of comma and split using line breaks*/
                out.println(outputString);//no comma
                out.close();  //closes variable when finished
            } catch (IOException e) {}
        }
    }
      //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="getFileContent">
    /** To read specific file which is known just use InitializationUtility.getFileContent(FileName) */
    public String getFileContent(File myfile){
        BufferedReader inputVal = null;
        String line, myString="";
       if(myfile != null){
           try {
               inputVal=new BufferedReader(new FileReader(myfile));
               while ((line=inputVal.readLine()) != null ){
                   /*get all the string in one variable(myString) using '\n' to indicate end of line*/
                   myString += line + "\n";
               }
           }
           catch (IOException e) {}
           finally {
               // if the file opened okay, make sure we close it
               if (inputVal != null) {
                   try {
                       inputVal.close();
                   } catch (IOException ioe) {}
              }
           }
       }
        return myString.trim(); //return the string
     }
     //</editor-fold>
}
