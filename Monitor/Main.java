package TrabalhoBarbearia;

public class Main {
    public static void main(String[] args){
        Barbearia barbearia = new Barbearia();

        Barbeiro barbeiro = new Barbeiro(barbearia);
        Cliente cliente1  = new Cliente(barbearia);
        Cliente cliente2  = new Cliente(barbearia);
        Cliente cliente3  = new Cliente(barbearia);
        Cliente cliente4  = new Cliente(barbearia);
        Cliente cliente5  = new Cliente(barbearia);

        barbeiro.start();
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
    }
}
