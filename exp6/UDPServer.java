import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {

        DatagramSocket socket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];

        System.out.println("Server Started...");

        // Receive packet
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        socket.receive(receivePacket);

        // Convert received data to String
        String sentence = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        System.out.println("Received: " + sentence);

        // Replace abbreviations
        sentence = sentence.replaceAll("\\btbh\\b", "to be honest");
        sentence = sentence.replaceAll("\\big\\b", "I guess");
        sentence = sentence.replaceAll("\\btbf\\b", "to be fair");
        sentence = sentence.replaceAll("\\batm\\b", "at the moment");
        sentence = sentence.replaceAll("\\birl\\b", "in real life");
        sentence = sentence.replaceAll("\\blol\\b", "laughing out loud");
        sentence = sentence.replaceAll("\\basap\\b", "as soon as possible");
        sentence = sentence.replaceAll("\\bomg\\b", "oh my God");
        sentence = sentence.replaceAll("\\bttyl\\b", "talk to you later");
        sentence = sentence.replaceAll("\\bidk\\b", "I don't know");
        sentence = sentence.replaceAll("\\bnvm\\b", "never mind");
        sentence = sentence.replaceAll("\\bidc\\b", "I don't care");

        // Convert translated sentence to bytes
        byte[] sendData = sentence.getBytes();

        // Send translated sentence back to client
        DatagramPacket sendPacket =
                new DatagramPacket(
                        sendData,
                        sendData.length,
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );

        socket.send(sendPacket);

        System.out.println("Translation Sent.");

        socket.close();
    }
}
 
