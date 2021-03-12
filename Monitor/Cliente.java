package TrabalhoBarbearia;

public class Cliente extends Thread {

    private Barbearia barbearia;

    public Cliente(Barbearia barbearia) { this.barbearia = barbearia; }

    public void run() {
        try {
            barbearia.corte_cabelo();
            System.out.println("Cliente: teve cabelo cortado");
            Thread.sleep(2500);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}