package org.example;

import com.phidget22.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
        DigitalInput di1 = new DigitalInput();
        DigitalInput di2 = new DigitalInput();
        di1.setChannel(0);
        di2.setChannel(1);

// si lcd
        LCD lcd = new LCD();
        lcd.open();
// si lcd
        di1.open(500);
        di2.open(500);

        if (di1.getAttached() && di2.getAttached()) {

            System.out.println("Device Name: " + di1.getDeviceName());
            System.out.println("Device Label: " + di1.getDeviceSerialNumber());
            System.out.println(di1.getDeviceID());
            System.out.println(di1.getDeviceChannelCount(ChannelClass.DIGITAL_INPUT));
            System.out.println(di1.getDeviceChannelCount(ChannelClass.DIGITAL_OUTPUT));
            System.out.println(di1.getDeviceChannelCount(ChannelClass.VOLTAGE_INPUT));
            System.out.println(di1.getDeviceChannelCount(ChannelClass.LCD));

            System.out.println("Device Name: " + lcd.getDeviceName());
            System.out.println("Device Label: " + lcd.getDeviceSerialNumber());
            System.out.println(lcd.getDeviceID());            System.out.println(lcd.getDeviceChannelCount(ChannelClass.DIGITAL_INPUT));
            System.out.println(lcd.getDeviceChannelCount(ChannelClass.DIGITAL_OUTPUT));
            System.out.println(lcd.getDeviceChannelCount(ChannelClass.VOLTAGE_INPUT));
            System.out.println(lcd.getDeviceChannelCount(ChannelClass.LCD));

            System.out.println("etat ch1:" + di1.getState());
            System.out.println("etat ch2:" + di2.getState());

            DigitalOutput do1 = new DigitalOutput();
            DigitalOutput do2 = new DigitalOutput();

            do1.setChannel(0);
            do2.setChannel(1);
            do1.open(500);
            do2.open(500);
            while (true) {
                // clignotement de l'entrée 0 et 1.
                do1.setState(true);
                do2.setState(false);
                Thread.sleep(500);
                do1.setState(false);
                do2.setState(true);
                Thread.sleep(500);
                // arret clignotement par une touche du clavier
                if (System.in.available() > 0) {
                    System.in.read();
                    do1.setState(false);
                    do2.setState(false);
                    break;
                }
            }
            //mise a 1 sortie 0 si entree0
            while (true) {
                do1.setState(di1.getState());
                do2.setState(di2.getState());

                // arret  par une touche du clavier
                if (System.in.available() > 0) {
                    System.in.read();
                    break;
                }
            }
            // fermeture du phidget
            di1.close();
            di2.close();
        }

    }
}
