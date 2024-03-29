package com.vectorutilities;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;

public class Menu {
    LinearOpMode parent;
    ArrayList<MenuItem> items = new ArrayList<>();

    int menuActiveItem = 0;

    boolean buttonPressed = false;

    public Menu(LinearOpMode p) {parent = p;}

    public void add(MenuItem m) { items.add(m); }

    public void display() {

        for (int i = 0; i<items.size(); i++) {
            if (i == menuActiveItem) {
                parent.telemetry.addLine(">>> "+items.get(i).getRepr());
            }
            else {
                parent.telemetry.addLine(items.get(i).getRepr());
            }
        }
    }

    public void update() {
        double JOYSTICK_THRESHOLD = 0.10;
        int delay = 250;
        if ((parent.gamepad1.right_stick_y > JOYSTICK_THRESHOLD) || parent.gamepad1.a) {
            items.get(menuActiveItem).down();
            parent.sleep(delay);
        }
        else if ((parent.gamepad1.right_stick_y < -JOYSTICK_THRESHOLD) || parent.gamepad1.y) {
            items.get(menuActiveItem).down();
            parent.sleep(delay);
        }

        else if (parent.gamepad1.b && !buttonPressed) {
            menuActiveItem++;
            if (menuActiveItem>=items.size()) {
                menuActiveItem = 0;
            }
            buttonPressed = true;
            parent.sleep(delay);
        }

        else if (parent.gamepad1.x && !buttonPressed) {
            menuActiveItem--;
            if (menuActiveItem<0) {
                menuActiveItem = items.size() - 1;
            }
            buttonPressed = true;
            parent.sleep(delay);
        }
        else { buttonPressed = false; };

    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }
}
