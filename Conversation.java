/**
 * The Conversation class uses a chatbot that records user inputs and outputs as a transcript and can run many rounds deciding by the user when doing interaction.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
class Conversation implements ConversationRequirements {

  // Attributes 
  ArrayList<String> transcript;
  int position;

  /**
   * Constructor 
   * It initializes the transcript, which is the whole records of all inputs and outputs, and the positions record where we are in the transcript.
   */
  Conversation() {
    transcript = new ArrayList<String>(); 
    position = 0;
  }

  /**
   * Starts and runs the conversation with the user
   * It does the chatting rounds, the greeting from the chatbot, the reaction we give to the chatbot, the reply for the inputString and the goodbye the goodbye after the conversation.
   */
  public void chat() {
    Scanner input=new Scanner(System.in);

    System.out.println("How many rounds?");
    int round=input.nextInt();
    input.nextLine();

    String greeting="Hi there! What's on your mind?";
    System.out.println(greeting);
    transcript.add(greeting);
    position= position + 1;

  
    for (int i= 0; i < round; i++) {
      String inputString= input.nextLine();
      transcript.add(inputString);
      position= position + 1;


      String reply= respond(inputString);
      System.out.println(reply);
      transcript.add(reply);
      position= position + 1;
    }

    String goodbye= "See ya!";
    System.out.println(goodbye);
     transcript.add(goodbye);
    position= position+1;
    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println();
    System.out.println("TRANSCRIPT:");
    for (int i=0; i<position; i++){
      System.out.println(transcript.get(i));
    }
    
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String[] words= inputString.split(" ");
    boolean mirrored=false;
    String returnString = ""; 
    String lastWord=words[words.length - 1];

    if (lastWord.charAt(lastWord.length() - 1) == '.'||lastWord.charAt(lastWord.length() - 1) == '?'||lastWord.charAt(lastWord.length() - 1) == '!'){
     words[words.length - 1] = lastWord.substring(0, lastWord.length() - 1);
    }
    for (int i=0; i<words.length;i++){
      String word=words[i].toLowerCase();
      if ("i".equals(word)){
        words[i]= "you";
        mirrored=true; 
      } else if ("me".equals(word)){
        words[i]= "you";
        mirrored=true; 
      } else if ("am".equals(word)){
        words[i]= "are";
        mirrored=true; 
      } else if ("you".equals(word)){
        if (i==0){
          words[i]= "I";
        } else {
          words[i]= "me";
        }
        mirrored=true; 
      } else if ("my".equals(word)){
        words[i]= "your";
        mirrored=true; 
      } else if ("your".equals(word)){
        words[i]= "my";
        mirrored=true; 
      }  else if ("i'm".equals(word)){
        words[i]= "you're";
        mirrored=true; 
      } else if ("you're".equals(word)){
        words[i]= "I'm";
        mirrored=true; 
      } 
    }
    if (mirrored==true){
        returnString=String.join(" ",words)+"?";
        returnString=returnString.substring(0,1).toUpperCase()+returnString.substring(1);
    }else{
      String[] canned={"Mmm-hm.","I see.","Interesting!","Wow.","Tell me more about that!"};
      Random rand=new Random(); 
      returnString= canned[rand.nextInt(canned.length)];
    }
    return returnString; 
  }


   /**
     * It runs the program by creating a conversation, running the chat, and then printing the transcript.
     * @param arguments the arguments
     */
  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
