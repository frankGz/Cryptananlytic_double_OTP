package OTP;

import util.CryptoTools;

/*
	Once upon a time, Alice wanted to send a physical letter to Bob but she knew that the courier, Eve, may open it and read it. To prevent that, she puts the letter in a box, puts her padlock on the box, and then locks it with her key. Upon receipt of the box, Bob (who doesn't have Alice's key and, hence, cannot open the box) puts his padlock on the box, next to Alice's, locks it with his key, and then returns the box to Alice. Upon receipt, she unlocks her padlock; removes it off the box; and return the box to Bob. When Bob receives it, he unlocks his padlock; opens the box; and reads the letter. This three-pass protocol enables Alice to send the letter physically to Bob in a secure manner even though she and Bob don't share a secret key. The security derives from the fact that the box is always locked while in transit. 
	
	Fast forward to today and do this with modern communication and a modern cipher. Alice's letter is a 16-character English sentence (i.e. made up of 128 bits because each character is a byte and each byte has 8 bits). Her cipher is OTP and her key is 128-bit long. Bob also uses OTP with his own 128-bit key. Eve intercepts the three exchanged messages (expressed as hex strings):
	
	  Alice Sends: 	0x 0A4F0C08003503492F247442105B5757
	  Bob Sends: 	0x 5E2769286B507A69494B066252343579
	  Alice Sends: 	0x 170708454B1116002A2E2111725F5000
	
	(Note that each message is made up of 128 bits.) 
	Can Eve crack the OTP? If so, find the English message; otherwise, argue that it is impossible. 
 */
public class activity_B1 {
	public static void main(String[] args) {
		
		String aTob1_hex = "0A4F0C08003503492F247442105B5757"; // alice encry with kA
		String bToa1_hex = "5E2769286B507A69494B066252343579"; // bob encry with kB
		String aTob2_hex = "170708454B1116002A2E2111725F5000"; // alice decry with kA
		
		byte[] CT1 = CryptoTools.hexToBytes(aTob1_hex);
		byte[] CT2 = CryptoTools.hexToBytes(bToa1_hex);
		byte[] PT2 = CryptoTools.hexToBytes(aTob2_hex);
		
		
		/*
		 * PT1 XOR kA = CT1
		 * CT1 XOR kB = CT2 ==> kB = CT1 XOR CT2
		 * CT2 XOR kA = PT2 ==> kA = PT2 XOR CT2
		 * PT2 XOR kB = PT1
		 * 
		 * 
		 */
		
		byte[] kB = new byte[CT1.length]; //result: The key for Bob.
		for(int i = 0; i < CT1.length;i++) {
			kB[i] = (byte) (CT1[i] ^ CT2[i]);
		}
		
		byte[] kA = new byte[CT1.length]; //result: I am Alice's key
		for(int i = 0; i < CT1.length;i++) {
			kA[i] = (byte) (PT2[i] ^ CT2[i]);
		}
		
		byte[] PT1 = new byte[CT1.length]; //result: Come to LAS1002.
		for(int i = 0; i < CT1.length;i++) {
			PT1[i] = (byte) (PT2[i] ^ kB[i]);
		}
		
		
		
		for(int i = 0; i < PT1.length;i++) {
			System.out.print((char)PT1[i]);
		}
	}
}
