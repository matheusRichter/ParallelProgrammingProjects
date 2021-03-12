package TrabalhoBarbearia;

public class Barbeiro extends Thread {

    private Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) { this.barbearia = barbearia; }

    public void run(){
        while (true) {
            try {
                System.out.println("Barbeiro: chamou cliente");
                barbearia.pegue_proximo_cliente();
                Thread.sleep(2500);
                System.out.println("Barbeiro: cortou cabelo do cliente");
                barbearia.termine_corte();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}