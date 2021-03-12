package TrabalhoBarbearia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbearia {

    // Gates
    final Lock gate_M = new ReentrantLock();
    Condition gate_A = gate_M.newCondition(); // cadeira_ocupada
    Condition gate_B = gate_M.newCondition(); // porta_aberta
    Condition gate_C = gate_M.newCondition(); // barbeiro_disponível
    Condition gate_D = gate_M.newCondition(); // cliente_saiu

    //Variáveis permanentes
    boolean barbeiro = false;
    boolean cadeira  = false;
    boolean aberta   = false;

    public Barbearia(){}

    public void corte_cabelo() throws InterruptedException {
        gate_M.lock();
        try {
            while (barbeiro == false) gate_C.await();
            barbeiro = false;
            cadeira = true;
            gate_A.signal();
            while (aberta == false) gate_B.await();
            aberta = false;
            gate_D.signal();
        } finally {
            gate_M.unlock();
        }
    }

    public void pegue_proximo_cliente() throws InterruptedException {
        gate_M.lock();
        try {
            barbeiro = true;
            gate_C.signal();
            while (cadeira == false) gate_A.await();
            cadeira = false;
        } finally {
            gate_M.unlock();
        }
    }

    public void termine_corte() throws InterruptedException {
        gate_M.lock();
        try{
            aberta = true;
            gate_B.signal();
            while (aberta == true) gate_D.await();
        } finally {
            gate_M.unlock();
        }
    }
}