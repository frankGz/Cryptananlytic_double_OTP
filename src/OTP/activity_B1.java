package OTP;

import util.CryptoTools;

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
