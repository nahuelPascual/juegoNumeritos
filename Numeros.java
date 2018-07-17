package numeritosgame;
import java.util.Scanner;

/**
 * @author NAHUEL
 */
public class Numeros {
    
    int intentos = 0;
    boolean ganaste = false ;
    String [][] tabla = new String [10][4];
    Scanner scan = new Scanner(System.in);
    
    public void Jugar(){
        String numeroUser = null;
        String numeroPC = inicializarNumPC();
        
        explicarJuego();       
        
        while(!ganaste && intentos<10){
            numeroUser = adivinar(numeroUser);
            verificar(numeroUser, numeroPC);
            showTabla();
        }
        if(ganaste){
            System.out.println("MUY BIEN, GANASTE!");
        } else {
            System.out.println("Mala suerte! el numero era: " + numeroPC);
        }
        replay();
    }
    
    private void replay (){
            System.out.print("Jugar de nuevo? S/N\t");
            char opc = scan.next().charAt(0);
            
            if(opc=='s' || opc=='S') {
                intentos = 0;
                ganaste = false;
                System.out.println("\n\n");
                Jugar();
            }    
    }
    private void explicarJuego(){
        System.out.println("El objetivo del juego es adivinar el numero de 4 digitos en que está pensando la computadora.");
        System.out.println("* El número no tiene dígitos iguales.");
        System.out.println("* Tras cada intento, el programa indica la cantidad de digitos bien (están en su lugar)");
        System.out.println("  cantidad de dígitos regulares (pertenecen al número pero no están en su sitio)");
        System.out.println("  y cantidad de dígitos mal (no pertenecen al número).");
        System.out.println("* Tenes un máximo de 10 intentos.");
        System.out.println("");
    }
    
    private String adivinar(String numeroUser){
        String num;
        do{
            System.out.print("\nIngresa un número de 4 digitos: ");
            num = scan.next();
        } while ( !num.matches("[0-9]*") || num.length()!=4);
        
        intentos++;
        
        return num;
    }
    
    private String inicializarNumPC(){
        int [] num = new int [4];
        for(int i=0; i<4; i++){
            do{
                num[i] = (int) (Math.random()*10);
            } while (seRepite(num[i], i, num));
        }
        return String.valueOf(num[0]) + num[1] + num[2] + num[3];
    }
    
    private boolean seRepite(int num, int pos, int numero[]){
        for(int i=0;i<pos;i++){
            if(numero[i] == num){
                return true;
            }
        }
        return false;
    }
    
    private void verificar(String numUser, String numPC){
        int bien=0,
            regular=0,
            mal=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(numUser.charAt(i) == numPC.charAt(j)){
                    if(i==j){
                        bien++;
                    } else {
                        regular++;
                    }
                }
            }
        }
        mal = 4 - bien - regular;
        cargarTabla(numUser, bien, regular, mal);
        if(bien==4) {
            ganaste = true;
        }
    }
    
    private void cargarTabla (String numUser, int bien, int regular, int mal){
        tabla[intentos-1][0] = String.valueOf(bien);
        tabla[intentos-1][1] = String.valueOf(regular);
        tabla[intentos-1][2] = String.valueOf(mal);
        tabla[intentos-1][3] = numUser;
    }
    
    private void showTabla(){
        System.out.println("Bien\t"
            + "Regular\t"
            + "Mal\t"
            + "Intentos");
        
        for(int i=0; i<intentos; i++){
            for(int j=0; j<4; j++){
                    System.out.print(tabla[i][j]);
                if(j==3) {
                    System.out.print(" (" + (i+1) + ")");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
    }  
}
