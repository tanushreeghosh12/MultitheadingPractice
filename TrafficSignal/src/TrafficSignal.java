/**
 * Created by 718895 on 12/19/2018.
 */
public class TrafficSignal {

        int status=1;
        int jobCount = 1;
        public static void main(String[] args) {

            TrafficSignal trafficSignal = new TrafficSignal();

            A1 a=new A1(trafficSignal);
            B1 b=new B1(trafficSignal);
            C1 c=new C1(trafficSignal);

            a.start();
            b.start();
            c.start();
        }
    }

    class A1 extends Thread{
        TrafficSignal trafficSignal;

        A1(TrafficSignal trafficSignal){
            this.trafficSignal = trafficSignal;
        }

        @Override
        public void run() {
            try{
                synchronized (trafficSignal) {

                    for (int i = 0; i < 4; i++) {

                        while(trafficSignal.status!=1){
                            trafficSignal.wait();
                        }

                        System.out.print(" Thread : 1 JobCount: " + trafficSignal.jobCount++ + "\n");
                        trafficSignal.status = 2;
                        trafficSignal.notifyAll();
                    }

                }
            }catch (Exception e) {
                System.out.println("Exception 1 :"+e.getMessage());
            }

        }

    }

    class B1 extends Thread{

        TrafficSignal trafficSignal;

        B1(TrafficSignal trafficSignal){
            this.trafficSignal = trafficSignal;
        }

        @Override
        public void run() {

            try{
                synchronized (trafficSignal) {

                    for (int i = 0; i < 3; i++) {

                        while(trafficSignal.status!=2){
                            trafficSignal.wait();
                        }

                        System.out.print(" Thread : 2 JobCount: " + trafficSignal.jobCount++ + "\n");
                        trafficSignal.status = 3;
                        trafficSignal.notifyAll();
                    }

                }
            }catch (Exception e) {
                System.out.println("Exception 2 :"+e.getMessage());
            }

        }
    }


    class C1 extends Thread{

        TrafficSignal trafficSignal;

        C1(TrafficSignal trafficSignal){
            this.trafficSignal = trafficSignal;
        }

        @Override
        public void run() {

            try{
                synchronized (trafficSignal) {

                    for (int i = 0; i < 3; i++) {

                        while(trafficSignal.status!=3){
                            trafficSignal.wait();
                        }

                        System.out.print(" Thread : 3 JobCount: " + (trafficSignal.jobCount++) + "\n");
                        trafficSignal.status = 1;
                    }

                }
            }catch (Exception e) {
                System.out.println("Exception 3 :"+e.getMessage());
            }

        }

}
