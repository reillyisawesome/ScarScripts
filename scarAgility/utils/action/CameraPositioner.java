package scripts.scarAgility.utils.action;

import org.tribot.api2007.Camera;

import scripts.scarAgility.utils.action.CameraPositioner.Angle;
import scripts.scarAgility.utils.action.CameraPositioner.Rotation;

public class CameraPositioner {

    private final Rotation rotation;
    private final Angle angle;

    public CameraPositioner(){
        rotation = new Rotation();
        rotation.setName("Camera Rotation Thread");
        rotation.start();

        angle = new Angle();
        angle.setName("Camera Angle Thread");
        angle.start();
    }

    public void setRotation(int degrees){
        if (rotation.isInAction()){
            return;
        }
        synchronized (rotation) {
            rotation.setDegrees(degrees);
            rotation.notify();
        }
    }

    public void setAngle(int degrees){
        if (angle.isInAction()){
            return;
        }
        synchronized (angle) {
            angle.setDegrees(degrees);
            angle.notify();
        }
    }

    public class Rotation extends Thread {

        private int degrees;
        private boolean inAction;

        @Override
        public void run(){
            Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_KEYS);
            while (true) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e){

                    }
                    inAction = true;
                    Camera.setCameraRotation(degrees);
                    inAction = false;
                }
            }
        }

        public boolean isInAction() {
            return inAction;
        }

        public void setDegrees(int degrees){
            this.degrees = degrees;
        }

    }

    public class Angle extends Thread {

        private int degrees;
        private boolean inAction;

        @Override
        public void run(){
            Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_KEYS);
            while (true){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e){

                    }
                    inAction = true;
                    Camera.setCameraAngle(degrees);
                    inAction = false;
                }
            }
        }

        public boolean isInAction() {
            return inAction;
        }

        public void setDegrees(int degrees){
            this.degrees = degrees;
        }

    }


}
