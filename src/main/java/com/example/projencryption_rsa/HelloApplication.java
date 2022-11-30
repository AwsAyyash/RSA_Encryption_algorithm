package com.example.projencryption_rsa;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private Label outputLabel = new Label("output");
    private File file1;

    // public static void main(String[] args) throws Exception {



        /*String x= 'Z' +"";
        System.out.println(x);
        x = x.toLowerCase();
        System.out.println(x);
        System.out.println(rsa.indexOf('z'));
        rsa.getPrimes();*/


       /* int d= new BigInteger(""+ 29).modInverse(new BigInteger(264+"")).intValue();
        System.out.println(d); // 173
        System.out.println();*/


        /*int encrypted = rsa.encrypt( (('o' + "").trim().toLowerCase().charAt(0))      );


        System.out.println(encrypted);

        System.out.println(rsa.decrypt(encrypted));


        System.out.println("aws");*/

    //File file = new File("src/input.txt");
    //String toEncrypt = Operations.readFileForEncrypt(file);

    // File file11 = new File("C:\\Users\\hp\\Desktop\\intelijWorkSpace\\alrorithm-course-projects\\proj3-fx\\proj-encryption_RSA\\fileHasEncryptedMsg.txt");
    //System.out.println(file11.getParent());
    //Operations.readFileForEncrypt(file1);
    //String decryptedMsg = "";
        /*for (int i = 0; i < toEncrypt.length(); i++) {
            decryptedMsg += rsa.encrypt(toEncrypt.charAt(i)) + " ";
        }*/

    //String header = rsa.getD() + "\n" + rsa.getn() + "\n"; // this is the private key

    //decryptedMsg = header + decryptedMsg;

    //Operations.writeToFile(decryptedMsg);

    ////////////////
    // Operations.readFileForDecrypt(new File("outpu"));

    //  launch();


    //}

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 70, 10));

        Label welcomeLabel = new Label("Welcome to RSA Encryption/Decryption Algorithm");
        welcomeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeLabel.setTextFill(Color.DARKGRAY);

        outputLabel.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));
        outputLabel.setTextFill(Color.RED);
        borderPane.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);

        VBox vBox = new VBox(10);


        Button readFileButton = new Button("Read File");
        readFileButton.setOnAction(actionEvent -> {
            fileStage(outputLabel).show();
        });

        vBox.getChildren().addAll(readFileButton);

        borderPane.setRight(vBox);

        VBox vBoxCenter = new VBox(10);
        Button encrypt = new Button("Encrypt");
        Button decrypt = new Button("Decrypt");

        vBoxCenter.getChildren().addAll(encrypt, decrypt);


        encrypt.setOnAction(actionEvent -> {

            try {
                if (file1 != null) {
                    String res = Operations.readFileForEncrypt(file1);
                    outputLabel.setText("Your file is Encrypted:\n" + res);
                    file1 = null;
                } else {
                    outputLabel.setText("Read the file first!");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        decrypt.setOnAction(actionEvent -> {

            try {
                if (file1 != null) {

                    String res = Operations.readFileForDecrypt(file1);
                    outputLabel.setText("Your file is Decrypted:\n'" + res + "'");
                    file1 = null;
                } else {
                    outputLabel.setText("Read the file first!");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        borderPane.setCenter(vBoxCenter);
        borderPane.setBottom(outputLabel);
        Scene scene = new Scene(borderPane, 1020, 440);
        stage.setTitle("RSA ENCRYPTION/DECRYPTION");
        stage.setScene(scene);
        stage.show();
    }

    private Stage fileStage(Label outputLabel) {

        Stage inputFileStage = new Stage();
        inputFileStage.setTitle("File chooser");

        Label inputFileLabel = new Label("Please Choose the file");
        inputFileLabel.setFont(Font.font("Verdana", 20));

        BorderPane inputFileBorderPane = new BorderPane();
        inputFileBorderPane.setPadding(new Insets(15));

        inputFileBorderPane.setTop(inputFileLabel);

        Button fileButton = new Button("Browse the file");
        inputFileBorderPane.setCenter(fileButton);

        BorderPane.setAlignment(inputFileLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(fileButton, Pos.CENTER);

        Scene fileScene = new Scene(inputFileBorderPane, 400, 300);
        inputFileStage.setScene(fileScene);

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open the File");

        fileButton.setOnAction(actionEvent -> {


            File file = fileChooser.showOpenDialog(inputFileStage);

            if (file != null) {


                this.file1 = file;
                outputLabel.setText("Your file has been read!");
                try {
                    // Operations.readFileForEncrypt(file);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            inputFileStage.close();

        });

        return inputFileStage;
    }
}